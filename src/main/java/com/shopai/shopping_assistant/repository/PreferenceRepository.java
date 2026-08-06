package com.shopai.shopping_assistant.repository;

import com.shopai.shopping_assistant.entity.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferenceRepository extends JpaRepository<Preference,Long> {
    Optional<Preference> findByUserId(Long userId);
}
