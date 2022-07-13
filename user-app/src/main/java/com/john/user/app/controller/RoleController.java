package com.john.user.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.john.boot.common.dto.PageRequest;
import com.john.boot.mysql.util.PageUtil;
import com.john.user.app.entity.Role;
import com.john.user.app.service.RoleService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author john
 */
@AllArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @ApiOperation("page")
    @GetMapping("/page")
    public IPage<Role> page(PageRequest pageRequest) {
        return roleService.page(PageUtil.getPage(pageRequest));
    }

    @PostMapping
    public void save(@RequestBody Role role) {
        roleService.save(role);
    }

    @PutMapping
    public void update(@RequestBody Role role) {
        roleService.updateById(role);
    }

    @DeleteMapping
    public void delete(@RequestBody Set<String> ids) {
        roleService.removeByIds(ids);
    }
}
