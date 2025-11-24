package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.RoleRequest;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Role;

public interface RoleService {
    Role saveRole(RoleRequest roleRequest);

    Role getRoleByName(String name);

    Role getRoleById(Long id);
}
