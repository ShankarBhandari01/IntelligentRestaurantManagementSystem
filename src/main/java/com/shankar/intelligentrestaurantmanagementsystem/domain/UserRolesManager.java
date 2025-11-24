package com.shankar.intelligentrestaurantmanagementsystem.domain;

import com.shankar.intelligentrestaurantmanagementsystem.entity.User;
import com.shankar.intelligentrestaurantmanagementsystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRolesManager {
    private final RoleService roleService;

    public User addRoleToUser(User user, long roleId) {
        var role = roleService.getRoleById(roleId);
        user.setRoles(java.util.Set.of(role));
        return user;
    }

}
