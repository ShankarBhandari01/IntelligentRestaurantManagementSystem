package com.shankar.intelligentrestaurantmanagementsystem.repository;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Kot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KotRepository extends JpaRepository<Kot, Long> {
}
