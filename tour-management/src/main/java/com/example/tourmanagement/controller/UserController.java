package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.UserModel;
import com.example.tourmanagement.model.UserRole;
import com.example.tourmanagement.model.enumRole;
import com.example.tourmanagement.service.UserRoleService;
import com.example.tourmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserRoleService userRoleService;
    @Autowired
    public UserController(UserService userService, UserRoleService userRoleService){
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @GetMapping({"/", ""})
    public String viewHomePage(Model model){
        return "redirect:/user/load";
    }

    @GetMapping("/load")
    public String loadUser(Model model){
        List<UserModel> userModels = userService.getAllUser();
        model.addAttribute("ListUsers", userModels);
        return "user/user_home";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") UserModel userModel, RedirectAttributes redirectAttributes){
        this.userService.saveUser(userModel);
        redirectAttributes.addFlashAttribute("successMessage", "User added successfully!");
        return "redirect:/user/load";

    }

    @PostMapping("/delete/{id}")
    public String deleteUser(Model model, @PathVariable long id){
        this.userService.deleteUser(id);
        return "redirect:/user";
    }

    @PostMapping("update/{id}")
    public  String updateUser(Model model, @PathVariable long id, @ModelAttribute("user") UserModel updatedUserModel){
        Optional<UserModel> optionalUser = userService.findByID(id);
        if(optionalUser.isPresent()){
            updatedUserModel.setId(id);
            this.userService.saveUser(updatedUserModel);
            return "redirect:/user";
        }
        else{
            return "redirect:/user";
        }

    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model){
        UserModel userModel = new UserModel();
        model.addAttribute("user", userModel);
        List<UserRole> userRoles = userRoleService.getAllUserRole();
        model.addAttribute("userRoles",userRoles);
        return "user/add_user";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(Model model, @PathVariable long id){
        Optional<UserModel> user = userService.findByID(id);
        if(user.isPresent()){
            model.addAttribute("user", user.get());
            List<UserRole> userRoles = userRoleService.getAllUserRole();
            model.addAttribute("userRoles", userRoles);
            return "user/updated_user";
        }
        else{
            model.addAttribute("message", "User can not found!");
            return "redirect:/user_role";
        }
    }

    //Login function

    @GetMapping("/login")
    public String getLoginPage() {
        return "security/login_page";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model, RedirectAttributes redirectAttributes) {
        UserModel user = new UserModel();
        model.addAttribute("user", user);

        if (redirectAttributes.containsAttribute("errorMessage")) {
            model.addAttribute("errorMessage", redirectAttributes.getAttribute("errorMessage"));
        }
        return "security/registration_page";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute UserModel user, RedirectAttributes redirectAttributes) {
        Optional<UserModel> currentUser = userService.loadByEmail(user.getEmail());
        if(currentUser.isEmpty()) {
            user.setUserRole(enumRole.USER);
            userService.saveUser(user);

            return "redirect:/user/login?success";
            //return "redirect:/user/showUpdateNewForm/" + user.getId() ;
        }
        else{
            String message = "the email must be unique";
            redirectAttributes.addFlashAttribute("errorMessage", message);
            return "redirect:/user/registration";
        }
    }

    @PostMapping("update2/{id}")
    public String updateUser(Model model, @PathVariable long id, @ModelAttribute("user") UserModel updatedUserModel, RedirectAttributes redirectAttributes) {
        Optional<UserModel> optionalUser = userService.findByID(id);
        if (optionalUser.isPresent()) {
            UserModel existingUser = optionalUser.get();

            try {
                // Get all fields declared in the UserModel class
                Field[] fields = UserModel.class.getDeclaredFields();
                for (Field field : fields) {
                    // Set accessible to true to access private fields
                    field.setAccessible(true);

                    // Get the value of the field from the updatedUserModel
                    Object updatedValue = field.get(updatedUserModel);

                    // If the updatedValue is not null, update the corresponding field in the existingUser
                    if (updatedValue != null) {
                        field.set(existingUser, updatedValue);
                    }
                }

                userService.saveUser(existingUser);


                redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
                return "redirect:/user";



            } catch (IllegalAccessException e) {
                // Handle IllegalAccessException
                redirectAttributes.addFlashAttribute("errorMessage", "Failed to update user");
                return "redirect:/user";
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found");
            return "redirect:/user";
        }
    }

    




}

