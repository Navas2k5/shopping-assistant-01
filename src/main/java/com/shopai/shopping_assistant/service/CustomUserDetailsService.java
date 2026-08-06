package com.shopai.shopping_assistant.service;

import com.shopai.shopping_assistant.entity.User;
import com.shopai.shopping_assistant.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user= userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User not found"));
        System.out.println("EMAIL RECEIVED = " + email);


        System.out.println("PASSWORD FROM DB = " + user.getPassword());

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password("{noop}" + user.getPassword())
                .roles("USER")
                .build();
    }

}
