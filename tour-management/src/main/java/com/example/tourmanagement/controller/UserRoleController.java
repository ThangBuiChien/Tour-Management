package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.UserRole;
import com.example.tourmanagement.service.UserRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/role")
@PreAuthorize("hasRole('ADMIN')")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService){
        this.userRoleService = userRoleService;
    }

    @GetMapping({"/", ""})
    public String viewHomePage(Model model){
        return "redirect:/role/load";
    }

    @GetMapping("/load")
    public String getAllUserRole(Model model, RedirectAttributes redirectAttributes){

        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
        }
        model.addAttribute("listUserRoles",  userRoleService.getAllUserRole());
        return "role/userrole_home";

    }

    @PostMapping("/add")
    public String addUserRole(@ModelAttribute("role") UserRole role, RedirectAttributes redirectAttributes){
        this.userRoleService.saveUserRole(role);
        redirectAttributes.addFlashAttribute("successMessage", "User Role added successfully!");
        return "redirect:/role/load";

    }

    @PostMapping("/delete/{id}")
    public String deleteUserRole(@PathVariable Long id){
        Optional<UserRole> userRole = userRoleService.findByID(id);
        if(userRole.isPresent()){
            this.userRoleService.deleteUserRole(id);
            return "redirect:/role";
        }
        else{
            return "redirect:/role";

        }

    }

    // Endpoint to update an existing department
    @PostMapping("/update/{id}")
    public String updateUserRole(@PathVariable Long id, @ModelAttribute("userRole") UserRole updatedUserRole) {
        Optional<UserRole> optionalUserRole = userRoleService.findByID(id);
        if (optionalUserRole.isPresent()) {
            updatedUserRole.setId(id);
            userRoleService.saveUserRole(updatedUserRole);
            return "redirect:/role";
        } else {
            return "redirect:/role";
        }
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model){
        UserRole role = new UserRole();
        model.addAttribute("role", role);
        return "role/add_user_role";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(Model model, @PathVariable long id){
        Optional<UserRole> userRole = userRoleService.findByID(id);
        if(userRole.isPresent()){
            model.addAttribute("userRole", userRole.get());
            return "role/updated_userrole";
        }
        else{
            model.addAttribute("message", "User role is can not found!");
            return "redirect:/role";
        }
//        model.addAttribute("department", department.get());
//        return "department/updated_department";
    }


}
