package com.shopai.shopping_assistant.service;

import com.shopai.shopping_assistant.entity.Preference;
import com.shopai.shopping_assistant.entity.Product;
import com.shopai.shopping_assistant.entity.Purchase;
import com.shopai.shopping_assistant.repository.PreferenceRepository;
import com.shopai.shopping_assistant.repository.ProductRepository;
import com.shopai.shopping_assistant.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {
    PreferenceRepository preferenceRepository;
    ProductRepository productRepository;
    PurchaseRepository purchaseRepository;

    public RecommendationService(PreferenceRepository preferenceRepository,ProductRepository productRepository,PurchaseRepository purchaseRepository){
        this.preferenceRepository=preferenceRepository;
        this.productRepository=productRepository;
        this.purchaseRepository=purchaseRepository;
    }

    public List<Product> getRecommendations(Long userId){
        Preference preference = preferenceRepository.findByUserId(userId).orElseThrow(()->
                new RuntimeException("Preference not found for user: "+userId));

        List<Product> products = productRepository.findByCategoryAndPriceLessThanEqual(preference.getPreferredCategory(),preference.getMaxBudget());
        List<Purchase> purchases = purchaseRepository.findByUserId(userId);

        List<Product> recommendations = new ArrayList<>();
        for(Product product:products){
            boolean alreadyPurchased = false;
            for(Purchase purchase:purchases){
                if(purchase.getProduct().getId().equals(product.getId())){
                    alreadyPurchased=true;
                    break;
                }
            }
            if(!alreadyPurchased){
                recommendations.add(product);
            }
        }
        List<Product> preferredBrandProducts = new ArrayList<>();
        List<Product> otherProducts = new ArrayList<>();
        for(Product product:recommendations){
            if(product.getBrand().equalsIgnoreCase(preference.getPreferredBrand())){
                preferredBrandProducts.add(product);
            }
            else
                otherProducts.add(product);
        }

        preferredBrandProducts.addAll(otherProducts);
        return preferredBrandProducts;
    }
}
