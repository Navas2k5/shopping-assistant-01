package com.shopai.shopping_assistant.config;

import com.shopai.shopping_assistant.security.CustomLoginSyccessHandler;
import com.shopai.shopping_assistant.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomLoginSyccessHandler customLoginSyccessHandler;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService,CustomLoginSyccessHandler customLoginSyccessHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.customLoginSyccessHandler=customLoginSyccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login",
                                "/about",
                                "/register",
                                "/brands",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                .userDetailsService(customUserDetailsService)

                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(customLoginSyccessHandler)
                        .failureUrl("/login?error")
                        .permitAll()
                )

                .logout(logout -> logout

                        .logoutUrl("/logout")

                        .logoutSuccessUrl("/")

                        .invalidateHttpSession(true)

                        .deleteCookies("JSESSIONID")

                        .permitAll()

                );

        return http.build();
    }
}