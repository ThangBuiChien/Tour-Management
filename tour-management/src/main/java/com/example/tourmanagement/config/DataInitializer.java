package com.example.tourmanagement.config;

import com.example.tourmanagement.model.UserModel;
import com.example.tourmanagement.model.enumRole;
import com.example.tourmanagement.repository.UserRepo;
import com.example.tourmanagement.service.UserService;
import com.example.tourmanagement.service.impl.UserServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String ddlAuto = event.getApplicationContext().getEnvironment().getProperty("spring.jpa.hibernate.ddl-auto");

        // Check if ddl-auto is set to create or update
        if ("create".equalsIgnoreCase(ddlAuto) ) {
            // Initialize your data here
            UserModel user = new UserModel();
            user.setEmail("admin");
            user.setPassword("admin");
            user.setUserRole(enumRole.ADMIN);
            userService.saveUser(user);
        }

    }
}
