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
    private final UserRepo repo;

    private final PasswordEncoder passwordEncoder;


    public DataInitializer(UserRepo repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String ddlAuto = event.getApplicationContext().getEnvironment().getProperty("spring.jpa.hibernate.ddl-auto");

        // Check if ddl-auto is set to create or update
        if ("create".equalsIgnoreCase(ddlAuto) ) {
            // Initialize your data here
            UserModel userModel = new UserModel();
            userModel.setUserRole(enumRole.ADMIN);
            userModel.setEmail("admin");
            userModel.setPassword(passwordEncoder.encode("admin"));
            this.repo.save(userModel);
        }

    }
}
