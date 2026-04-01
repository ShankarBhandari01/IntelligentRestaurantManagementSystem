package com.shankar.intelligentrestaurantmanagementsystem.controller;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.RoleRequest;
import com.shankar.intelligentrestaurantmanagementsystem.dto.response.ApiResponse;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Role;
import com.shankar.intelligentrestaurantmanagementsystem.service.RoleService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class RolesController extends BaseController {
    private final RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<@NonNull ApiResponse<Role>> addRoles(@RequestBody @Valid RoleRequest roleRequest) {
        var response = roleService.saveRole(roleRequest);
        return created(response);
    }
}
