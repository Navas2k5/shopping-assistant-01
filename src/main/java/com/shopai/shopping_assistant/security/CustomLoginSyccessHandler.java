package com.shopai.shopping_assistant.security;

import com.shopai.shopping_assistant.entity.User;
import com.shopai.shopping_assistant.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginSyccessHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;

    public CustomLoginSyccessHandler(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(()->
                new RuntimeException("User not found"));

        response.sendRedirect("/home/"+user.getId());
    }
}
