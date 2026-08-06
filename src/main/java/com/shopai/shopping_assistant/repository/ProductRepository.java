package com.shopai.shopping_assistant.repository;

import com.shopai.shopping_assistant.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByCategoryAndPriceLessThanEqual(String category, BigDecimal maxBudget);

    @Query("select distinct p.category from Product p")
    Set<String> findAllCategories();

    @Query("select distinct p.brand from Product p")
    Set<String> findAllBrands();

    @Query("SELECT DISTINCT p.brand FROM Product p WHERE p.category = :category")
    Set<String> findBrandsByCategory(String category);

}
