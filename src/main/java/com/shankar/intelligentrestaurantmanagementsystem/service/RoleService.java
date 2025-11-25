package com.shankar.intelligentrestaurantmanagementsystem.service;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.RoleRequest;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role saveRole(RoleRequest roleRequest);

    Role getRoleByName(String name);

    Role getRoleById(Long id);

    Set<Role> getRoleByIdInList(Set<Long> roleIds);
}
