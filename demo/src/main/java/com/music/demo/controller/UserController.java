package com.music.demo.controller;

import com.music.demo.dao.User;
import com.music.demo.result.Result;
import com.music.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 获取用户信息
    @GetMapping("/{userId}")
    public Result<User> getUserProfile(@PathVariable Long userId) {
        User user = userService.getUserProfile(userId);
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error(404, "用户不存在");
        }
    }

    // 更新用户信息
    @PutMapping("/{userId}")
    public Result<Void> updateUserProfile(@PathVariable Long userId, @RequestBody User user) {
        user.setId(userId);
        userService.updateUserProfile(user);
        return Result.success(null);
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return Result.error(400, "用户名或密码不能为空");
        }

        if (userService.findUserByUsername(user.getUsername()) != null) {
            return Result.error(400, "用户名已存在");
        }

        userService.registerUser(user);
        return Result.success("注册成功");
    }

}