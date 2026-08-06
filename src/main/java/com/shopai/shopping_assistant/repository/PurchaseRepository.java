package com.shopai.shopping_assistant.repository;

import com.shopai.shopping_assistant.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
    List<Purchase> findByUserId(Long userId);
}
