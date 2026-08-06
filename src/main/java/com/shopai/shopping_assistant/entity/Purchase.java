package com.shopai.shopping_assistant.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name="purchases")
@Getter
@Setter
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private Product product;

    @Column(name="purchase_date")
    private LocalDateTime purchaseDate;

    @PrePersist
    public void setPurchaseDate(){
        if(purchaseDate==null){
            purchaseDate=LocalDateTime.now();
        }
    }
}


