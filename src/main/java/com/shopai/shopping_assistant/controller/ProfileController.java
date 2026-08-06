package com.shopai.shopping_assistant.controller;

import com.shopai.shopping_assistant.entity.Preference;
import com.shopai.shopping_assistant.entity.User;
import com.shopai.shopping_assistant.repository.ChatMessageRepository;
import com.shopai.shopping_assistant.repository.PreferenceRepository;
import com.shopai.shopping_assistant.repository.ProductRepository;
import com.shopai.shopping_assistant.repository.UserRepository;
import com.shopai.shopping_assistant.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@Controller
public class ProfileController {

    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final PreferenceRepository preferenceRepository;
    private final ProductService productService;

    public ProfileController(UserRepository userRepository,ChatMessageRepository chatMessageRepository,PreferenceRepository preferenceRepository,ProductService productService){
        this.userRepository=userRepository;
        this.chatMessageRepository=chatMessageRepository;
        this.preferenceRepository=preferenceRepository;
        this.productService=productService;
    }

    @GetMapping("/profile/{userId}")
    public String getProfile(@PathVariable Long userId, Model model){
        User user = userRepository.findById(userId).orElseThrow(()->
                new RuntimeException("User not found."));

        Preference preference= preferenceRepository.findByUserId(userId).orElseThrow(()->
                new RuntimeException("Preference not found"));



        model.addAttribute("user",user);
        model.addAttribute("preference",preference);
        model.addAttribute("categories",productService.getCategories());
        model.addAttribute("brands",productService.getBrandsByCategory(preference.getPreferredCategory()));


        return "profile";
    }

    @GetMapping("/about")
    public String aboutSection(){
        return "about";
    }

    @PostMapping("/profile/updatePreference")
    public String updatePreference(@RequestParam Long userId, @RequestParam String preferredCategory, @RequestParam String preferredBrand, @RequestParam BigDecimal maxBudget){
        Preference preference = preferenceRepository.findByUserId(userId).orElseThrow(()->
                new RuntimeException("Preference not found"));

        preference.setPreferredCategory(preferredCategory);
        preference.setPreferredBrand(preferredBrand);
        preference.setMaxBudget(maxBudget);

        preferenceRepository.save(preference);

        return "redirect:/profile/"+userId;
    }

    @GetMapping("/brands")
    @ResponseBody
    public Set<String> getBrands(@RequestParam String category){
        return productService.getBrandsByCategory(category);
    }


}
