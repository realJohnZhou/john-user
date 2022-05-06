package com.john.user.app.controller;

import com.john.user.app.entity.User;
import com.john.user.app.service.UserService;
import io.swagger.annotations.Api;
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
}
