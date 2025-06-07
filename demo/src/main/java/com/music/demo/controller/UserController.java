package com.music.demo.controller;

import com.music.demo.dao.User;
import com.music.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public User getUserProfile(@RequestParam Long userId) {
        return userService.getUserProfile(userId);
    }

    @PutMapping("/profile")
    public void updateUserProfile(@RequestBody User user) {
        userService.updateUserProfile(user);
    }
}