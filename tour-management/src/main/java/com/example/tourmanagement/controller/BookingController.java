    package com.example.tourmanagement.controller;

    import com.example.tourmanagement.depa.creator.PaymentFactory;
    import com.example.tourmanagement.depa.product.PaymentService;
    import com.example.tourmanagement.depa.creator.PaymentServiceFactory;
    import com.example.tourmanagement.model.*;
    import com.example.tourmanagement.service.TourService;
    import com.example.tourmanagement.service.InvoiceService;
    import com.example.tourmanagement.service.UserService;
    import jakarta.servlet.http.HttpSession;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.security.core.annotation.AuthenticationPrincipal;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Map;
    import java.util.Optional;
    import java.time.LocalDate;

    @Controller
    @RequestMapping("/book")
    @PreAuthorize("hasRole('USER')")
    public class BookingController {

        private final TourService tourService;
        private final InvoiceService invoiceService;
        private final UserService userService;

        private final PaymentServiceFactory paymentServiceFactory;


        public BookingController(TourService tourService, InvoiceService invoiceService, UserService userService, PaymentServiceFactory paymentServiceFactory) {
            this.tourService = tourService;
            this.invoiceService = invoiceService;
            this.userService = userService;
            this.paymentServiceFactory = paymentServiceFactory;
        }

        @GetMapping("/{tourId}")
        public String showBookingForm(@PathVariable Long tourId, Model model, RedirectAttributes redirectAttributes) {
            Optional<Tour> tour = tourService.findByID(tourId);
            if (tour.isPresent()) {
                Tour tourData = tour.get();
                int remainingCapacity = tourData.getRemainingCapacity();
                model.addAttribute("tour", tourData);
                model.addAttribute("remainingCapacity", remainingCapacity);
                model.addAttribute("invoice", new Invoice());
                model.addAttribute("startDate", tourData.getStartDate().toString());  // Adding start date to the model
                return "book/book";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Tour not found!");
                return "redirect:/tour/available";
            }
        }



        @PostMapping("/submit")
        public String submitBooking(@ModelAttribute Invoice invoice,
                                    @RequestParam("numMembers") int numMembers,
                                    @RequestParam("tourId") Long tourId,
                                    @RequestParam List<String> listOfMember,
                                    HttpSession session, Model model,
                                    RedirectAttributes redirectAttributes,
                                    @AuthenticationPrincipal UserDetails userDetails) {


            String userMail = userDetails.getUsername();
            Optional<UserModel> userOpt = userService.loadByEmail(userMail);
            if (userOpt.isEmpty()) {
                redirectAttributes.addFlashAttribute("errorMessage", "User not found!");
                return "redirect:/tour/available";
            }
            Long id = userOpt.get().getId();

            // Fetch the tour from the database
            Optional<Tour> tourOpt = tourService.findByID(tourId);
            if (tourOpt.isEmpty()) {
                redirectAttributes.addFlashAttribute("errorMessage", "Tour not found!");
                return "redirect:/tour/available";
            }

            Tour tour = tourOpt.get();

            int remainingCapacity = tour.getTourCapacity().getMaxMember() - tour.getRegister();
            if (numMembers > remainingCapacity) {
                redirectAttributes.addFlashAttribute("errorMessage", "Number of members exceeds the tour capacity!");
                return "redirect:/book/" + tour.getId();
            }

            tour.setRegister(tour.getRegister() + numMembers);
            tourService.saveTour(tour);

            invoice.setTour(tour);
            invoice.setUserModel(userOpt.get());



            // Check number of members
            if (numMembers <= 0) {
                redirectAttributes.addFlashAttribute("errorMessage", "Invalid number of members.");
                return "redirect:/tour/available";
            }

            // Check capacity constraints
            Capacity capacity = tour.getTourCapacity();
            if (numMembers > capacity.getMaxMember()) {
                redirectAttributes.addFlashAttribute("errorMessage", "Number of members exceeds the tour capacity!");
                return "redirect:/book/" + tour.getId();
            }

            // Calculate total price
            float tourPrice = tour.getTourPrice();
            invoice.setTotalPrice(numMembers * tourPrice);


            //Set status for invoice
            invoice.setStatus(InvoiceStatus.PENDING);

            if (listOfMember == null) {
                listOfMember = new ArrayList<>();
            }
            invoice.setListOfMember(listOfMember);




            // Save the invoice
            try {
                invoiceService.saveInvoice(invoice);
                session.setAttribute("currentInvoice", invoice);
                session.setAttribute("currentInvoiceId", invoice.getId());
                model.addAttribute("invoice", invoice);
                redirectAttributes.addFlashAttribute("successMessage", "Booking submitted successfully!");
                return "redirect:/book/payment/payment_home?invoiceId=" + invoice.getId() + "&tourId=" + tour.getId();

            } catch (Exception e) {
                System.err.println("Error saving invoice: " + e.getMessage());
                redirectAttributes.addFlashAttribute("errorMessage", "Failed to save booking.");
                return "redirect:/tour/available";
            }
        }

        @GetMapping("/payment/payment_home")
        public String paymentHome(@RequestParam("invoiceId") Long invoiceId, @RequestParam("tourId") Long tourId, Model model) {
            Optional<Invoice> invoiceOpt = Optional.ofNullable(invoiceService.findInvoiceById(invoiceId));
            Optional<Tour> tourOpt = tourService.findByID(tourId); // Ensure this method exists and works as expected

            if (invoiceOpt.isPresent() && tourOpt.isPresent()) {
                Invoice invoice = invoiceOpt.get();
                Tour tour = tourOpt.get();

                model.addAttribute("currentInvoice", invoice);
                model.addAttribute("tour", tour);
                model.addAttribute("startDate", tour.getStartDate().toString());  // Ensure dates are formatted as needed
                model.addAttribute("remainingCapacity", tour.getRemainingCapacity());
                model.addAttribute("totalPrice", invoice.getTotalPrice());  // Assuming total price is calculated at booking
            } else {
                model.addAttribute("errorMessage", "Invoice or Tour not found!");
                return "redirect:/errorPage";
            }
            return "payment/payment_home";
        }


        @PostMapping("/submitPayment")
        public String submitPayment(@RequestParam Map<String,
                String> params, HttpSession session, Model model,
                                    RedirectAttributes redirectAttributes,
                                    @RequestParam("paymentMethod") String paymentMethod,
                                    @RequestParam("amount") double amount) {
            Long invoiceId = (Long) session.getAttribute("currentInvoiceId"); // Assuming invoice ID is stored in the session
            Optional<Invoice> invoiceOpt = Optional.ofNullable(invoiceService.findInvoiceById(invoiceId));

            if (invoiceOpt.isEmpty()) {
                redirectAttributes.addFlashAttribute("errorMessage", "Invoice not found!");
                return "redirect:/payment/payment_home";
            }

            Invoice invoice = invoiceOpt.get();
            invoice.setPaymentAccount(params.get("paymentAccount"));
            invoice.setPayerName(params.get("payerName"));
            invoice.setPaymentDate(LocalDate.now());

            try {
                invoiceService.updateInvoice(invoice);
//                PaymentService paymentService = paymentServiceFactory.createPaymentService(paymentMethod);
                PaymentServiceFactory factory = PaymentFactory.getFactory(paymentMethod);
                PaymentService paymentService = factory.createPaymentService(paymentMethod);
                String message = paymentService.processPayment(amount);
                model.addAttribute("message", message);
                redirectAttributes.addFlashAttribute("successMessage", "Payment submitted successfully!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Error updating invoice: " + e.getMessage());
                return "redirect:/payment/payment_home";
            }

            return "payment/payment_complete";
        }




    }
