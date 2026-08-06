package com.shopai.shopping_assistant.controller;

import com.shopai.shopping_assistant.repository.PreferenceRepository;
import com.shopai.shopping_assistant.repository.UserRepository;
import com.shopai.shopping_assistant.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


    private final UserRepository userRepository;
    private final ProductService productService;
    private final PreferenceRepository preferenceRepository;

    public LoginController(UserRepository userRepository,ProductService productService,PreferenceRepository preferenceRepository){
        this.userRepository=userRepository;
        this.preferenceRepository=preferenceRepository;
        this.productService=productService;
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(required =false) String registered){
        model.addAttribute("categories",productService.getCategories());
        model.addAttribute("brands",productService.getBrands());

        if(registered!=null){
            model.addAttribute("success","Registration successful! Please login.");
        }
        return "login";
    }



}
