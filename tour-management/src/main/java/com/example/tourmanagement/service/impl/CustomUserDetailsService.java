package com.example.tourmanagement.service.impl;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import com.example.tourmanagement.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@NoArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> byLogin = userService.loadByEmail(username);

        if(byLogin.isEmpty()){
            return null;
        }
        String encodedPassword = passwordEncoder.encode(byLogin.get().getPassword()); // Assuming password is already encoded



        return User.builder()
                    .username(byLogin.get().getEmail())
                    .password(byLogin.get().getPassword())
                    .roles(byLogin.get().getUserRole().name())
                    .build();





    }
}
