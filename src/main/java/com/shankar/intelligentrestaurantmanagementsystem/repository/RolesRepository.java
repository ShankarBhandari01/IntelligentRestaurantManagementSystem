package com.shankar.intelligentrestaurantmanagementsystem.repository;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Role;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RolesRepository extends JpaRepository<@NonNull Role, @NonNull Long> {

    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    Optional<Role> findByName(String name);

    @Query("SELECT r FROM Role r WHERE r.id IN ?1")
    Optional<Set<Role>> getRoleByIdIn(Set<Long> ids);
}


