package com.academy.demo_ws.controller;

import com.academy.demo_ws.entity.UserEntity;
import com.academy.demo_ws.model.User;
import com.academy.demo_ws.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserEntity getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public UserEntity create(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public UserEntity update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
