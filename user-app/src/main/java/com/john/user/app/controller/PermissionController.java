package com.john.user.app.controller;

import com.john.user.app.entity.Permission;
import com.john.user.app.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author john
 */
@Api(tags = "permission api")
@AllArgsConstructor
@RestController
@RequestMapping("/permission")
public class PermissionController {
    private final PermissionService permissionService;

    @ApiOperation("save")
    @PostMapping
    public void save(@RequestBody List<Permission> permissionList) {
        permissionService.saveBatch(permissionList);
    }

    @PostMapping("/replace/{roleId}")
    public void replace(@PathVariable String roleId, @RequestBody Set<String> menuIds) {
        permissionService.replace(roleId, menuIds);
    }
}
