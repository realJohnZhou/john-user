package com.john.user.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.john.boot.common.dto.PageRequest;
import com.john.boot.mysql.util.PageUtil;
import com.john.user.app.entity.User;
import com.john.user.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * user controller
 *
 * @author john
 */
@Api(tags = "user")
@AllArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @ApiOperation("page")
    @GetMapping("/page")
    public Page<User> getPage(PageRequest request) {
        return userService.page(PageUtil.getPage(request));
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable String id) {
        return userService.getById(id);
    }

    @PostMapping
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping
    public void update(@RequestBody User user) {
        userService.updateById(user);
    }

    @DeleteMapping
    public void batchDelete(@RequestBody Set<String> ids) {
        userService.removeBatchByIds(ids);
    }

    @PutMapping("/reset/{id}")
    public void reset(@PathVariable String id) {
        // todo
    }
}
