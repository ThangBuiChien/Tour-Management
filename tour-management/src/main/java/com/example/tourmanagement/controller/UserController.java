package com.example.tourmanagement.controller;

import com.example.tourmanagement.model.User;
import com.example.tourmanagement.model.UserRole;
import com.example.tourmanagement.service.UserService;
import com.example.tourmanagement.service.UserRoleService;
import com.fasterxml.jackson.annotation.OptBoolean;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping({"/", ""})
    public String viewHomePage(Model model){
        return "redirect:/user/load";
    }

    @GetMapping("/load")
    public String loadUser(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("ListUsers", emps);
        return "user/user_home";
    }

    @PostMapping("/add")
    public String addUser(Model model, @ModelAttribute("user") User user){
        this.userService.saveUser(user);
        return "redirect:/user";

    }

    @PostMapping("/delete/{id}")
    public String deleteUser(Model model, @PathVariable long id){
        this.userService.deleteUser(id);
        return "redirect:/user";
    }

    @PostMapping("update/{id}")
    public  String updateUser(Model model, @PathVariable long id, @ModelAttribute("user") User updatedUser){
        Optional<User> optionalUser = userService.findByID(id);
        if(optionalUser.isPresent()){
            updatedUser.setId(id);
            this.userService.saveUser(updatedUser);
            return "redirect:/user";
        }
        else{
            return "redirect:/user";
        }

    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        List<User> users = userService.getAllDepartment();
        model.addAttribute("users", users);
        return "user/add_user";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(Model model, @PathVariable long id){
        Optional<User> user = userService.findByID(id);
        if(user.isPresent()){
            model.addAttribute("user", user.get());
            return "user/updated_user";
        }
        else{
            model.addAttribute("message", "User can not found!");
            return "redirect:/user_role";
        }
    }
}

