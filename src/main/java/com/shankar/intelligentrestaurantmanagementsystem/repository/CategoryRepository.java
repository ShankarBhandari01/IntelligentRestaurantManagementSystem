package com.shankar.intelligentrestaurantmanagementsystem.repository;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

}
