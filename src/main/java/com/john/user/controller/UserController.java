package com.john.user.controller;

import com.john.user.entity.User;
import com.john.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * user controller
 *
 * @author john
 */
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
}
