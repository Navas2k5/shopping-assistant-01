package com.shopai.shopping_assistant.controller;

import com.shopai.shopping_assistant.entity.Preference;
import com.shopai.shopping_assistant.entity.User;
import com.shopai.shopping_assistant.repository.PreferenceRepository;
import com.shopai.shopping_assistant.repository.ProductRepository;
import com.shopai.shopping_assistant.repository.UserRepository;
import com.shopai.shopping_assistant.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class RegisterController {

    private final UserRepository userRepository;
    private final PreferenceRepository preferenceRepository;
    private final ProductService productService;

    public RegisterController(UserRepository userRepository, PreferenceRepository preferenceRepository, ProductService productService){
        this.userRepository=userRepository;
        this.preferenceRepository=preferenceRepository;
        this.productService=productService;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("categories",productService.getCategories());
        model.addAttribute("brands",productService.getBrands());
        return "register";
    }


    @PostMapping("/register")
    public String registerNewUser(@RequestParam String name, @RequestParam String email,
                                  @RequestParam String password, @RequestParam String preferredCategory,
                                  @RequestParam String preferredBrand, @RequestParam BigDecimal maxBudget,
                                  Model model){


        if(userRepository.findByEmail(email).isPresent()){
            model.addAttribute("error","Email already exists");
            model.addAttribute("categories",productService.getCategories());
            return "register";
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user = userRepository.save(user);

        Preference preference = new Preference();
        preference.setPreferredBrand(preferredBrand);
        preference.setPreferredCategory(preferredCategory);
        preference.setMaxBudget(maxBudget);
        preference.setUser(user);
        preferenceRepository.save(preference);

        return "redirect:/login?registered";

    }
}
