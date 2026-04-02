package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.RoleRequest;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Role;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface RoleService {
    CompletableFuture<Role> saveRole(RoleRequest roleRequest);

    CompletableFuture<Role> getRoleByName(String name);

    CompletableFuture<Role> getRoleById(Long id);

    CompletableFuture<Set<Role>> getRoleByIdInList(Set<Long> roleIds);
}
