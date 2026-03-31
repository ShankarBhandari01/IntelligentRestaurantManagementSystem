package com.shankar.intelligentrestaurantmanagementsystem.repository;

import com.shankar.intelligentrestaurantmanagementsystem.entity.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItems, String> {
    List<MenuItems> findByCategoryId(String categoryId);

    List<MenuItems> findByIsActiveTrueAndIsDeletedFalse();

    boolean existsByItemNameEnIgnoreCaseAndCategoryId(String nameEn, String categoryId);
}
