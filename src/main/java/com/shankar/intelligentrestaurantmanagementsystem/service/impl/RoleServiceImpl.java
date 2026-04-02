package com.shankar.intelligentrestaurantmanagementsystem.service.impl;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.RoleRequest;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Role;
import com.shankar.intelligentrestaurantmanagementsystem.mapper.RoleMapper;
import com.shankar.intelligentrestaurantmanagementsystem.repository.RolesRepository;
import com.shankar.intelligentrestaurantmanagementsystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RolesRepository rolesRepository;
    private final RoleMapper roleMapper;

    @Async
    @Override
    public CompletableFuture<Role> saveRole(RoleRequest roleRequest) {
        Role role = roleMapper.toEntity(roleRequest);
        rolesRepository.save(role);
        return CompletableFuture.completedFuture(role);
    }

    @Async
    @Override
    public CompletableFuture<Role> getRoleByName(String name) {
        return CompletableFuture.completedFuture(rolesRepository.findByName(name).orElseThrow(() -> new RuntimeException("Role not found")));
    }

    @Async
    @Override
    public CompletableFuture<Role> getRoleById(Long id) {
        return CompletableFuture.completedFuture(rolesRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found")));
    }

    @Async
    @Override
    public CompletableFuture<Set<Role>> getRoleByIdInList(Set<Long> roleIds) {
        return CompletableFuture.completedFuture(rolesRepository.getRoleByIdIn(roleIds).orElseThrow(() -> new RuntimeException("Role not found")));
    }

}
