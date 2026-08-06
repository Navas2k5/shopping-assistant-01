package com.shopai.shopping_assistant.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="preferences")
@Getter
@Setter
@NoArgsConstructor
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preferred_category")
    private String preferredCategory;

    @Column(name = "preferred_brand")
    private String preferredBrand;

    @Column(name="max_budget")
    private BigDecimal maxBudget;

    @OneToOne
    @JoinColumn(name="user_id",nullable = false,unique = true)
    private User user;

}
