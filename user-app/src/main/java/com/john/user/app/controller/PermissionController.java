package com.john.user.app.controller;

import com.john.user.app.entity.Permission;
import com.john.user.app.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author john
 */
@AllArgsConstructor
@RestController
@RequestMapping("/permission")
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping
    public void save(@RequestBody List<Permission> permissionList) {
        permissionService.saveBatch(permissionList);
    }
}
