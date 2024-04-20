package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.UserRole;
import com.example.tourmanagement.service.UserRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/user_role")
public class UserRoleController  {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService){
        this.userRoleService = userRoleService;
    }

    @GetMapping({"/", ""})
    public String viewHomePage(Model model){
        return "redirect:/user_role/load";
    }

    @GetMapping("/load")
    public String getAllUserRole(Model model, RedirectAttributes redirectAttributes){

        if (redirectAttributes.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", redirectAttributes.getAttribute("successMessage"));
        }
        model.addAttribute("listUserRoles",  userRoleService.getAllUserRole());
        return "user_role/userrole_home";

    }

    @PostMapping("/add")
    public String addUserRole(@ModelAttribute("userRole") UserRole userRole, RedirectAttributes redirectAttributes){
        this.userRoleService.saveUserRole(userRole);
        redirectAttributes.addFlashAttribute("successMessage", "User Role added successfully!");
        return "redirect:/user_role/load";

    }

    @PostMapping("/delete/{id}")
    public String deleteUserRole(@PathVariable Long id){
        Optional<UserRole> userRole = userRoleService.findByID(id);
        if(userRole.isPresent()){
            this.userRoleService.deleteUserRole(id);
            return "redirect:/user_role";
        }
        else{
            return "redirect:/user_role";

        }

    }

    // Endpoint to update an existing department
    @PostMapping("/update/{id}")
    public String updateUserRole(@PathVariable Long id, @ModelAttribute("userRole") UserRole updatedUserRole) {
        Optional<UserRole> optionalUserRole = userRoleService.findByID(id);
        if (optionalUserRole.isPresent()) {
            updatedUserRole.setId(id);
            userRoleService.saveUserRole(updatedUserRole);
            return "redirect:/user_role";
        } else {
            return "redirect:/user_role";
        }
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model){
        UserRole userRole = new UserRole();
        model.addAttribute("userRole", userRole);
        return "user_role/add_userrole";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(Model model, @PathVariable long id){
        Optional<UserRole> userRole = userRoleService.findByID(id);
        if(userRole.isPresent()){
            model.addAttribute("userRole", userRole.get());
            return "user_role/updated_userrole";

        }
        else{
            model.addAttribute("message", "User role is can not found!");
            return "redirect:/user_role";
        }
//        model.addAttribute("department", department.get());
//        return "department/updated_department";
    }


}
