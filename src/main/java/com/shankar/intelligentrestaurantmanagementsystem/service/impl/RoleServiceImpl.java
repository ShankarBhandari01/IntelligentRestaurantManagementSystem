package com.shankar.intelligentrestaurantmanagementsystem.service.impl;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.RoleRequest;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Role;
import com.shankar.intelligentrestaurantmanagementsystem.mapper.RoleMapper;
import com.shankar.intelligentrestaurantmanagementsystem.repository.RolesRepository;
import com.shankar.intelligentrestaurantmanagementsystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RolesRepository rolesRepository;
    private final RoleMapper roleMapper;

    @Override
    public Role saveRole(RoleRequest roleRequest) {
        Role role = roleMapper.toEntity(roleRequest);
        rolesRepository.save(role);
        return role;
    }

    @Override
    public Role getRoleByName(String name) {
        return rolesRepository.findByName(name).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public Role getRoleById(Long id) {
        return rolesRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }

}
