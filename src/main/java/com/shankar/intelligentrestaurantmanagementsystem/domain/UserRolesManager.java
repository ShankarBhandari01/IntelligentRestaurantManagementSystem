package com.shankar.intelligentrestaurantmanagementsystem.domain;

import com.shankar.intelligentrestaurantmanagementsystem.entity.Role;
import com.shankar.intelligentrestaurantmanagementsystem.entity.User;
import com.shankar.intelligentrestaurantmanagementsystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserRolesManager {
    private final RoleService roleService;

    public User addRoleToUser(User user, List<Role> roles) {
        try {
            // get roles by id validation
            Set<Long> rolesIds = roles.stream().map(Role::getId).collect(Collectors.toSet());
            // get roles by id in list
            var role = roleService.getRoleByIdInList(rolesIds);
            // set roles to user
            user.setRoles(role);
            // return user
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
