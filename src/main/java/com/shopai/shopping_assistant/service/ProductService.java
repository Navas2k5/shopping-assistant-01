package com.shopai.shopping_assistant.service;

import com.shopai.shopping_assistant.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public Set<String> getCategories(){
        return productRepository.findAllCategories();
    }

    public Set<String> getBrands(){
        return productRepository.findAllBrands();
    }

    public Set<String> getBrandsByCategory(String category){
        return productRepository.findBrandsByCategory(category);
    }
}
