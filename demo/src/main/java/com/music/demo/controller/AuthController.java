package com.music.demo.controller;

import com.music.demo.dao.User;
import com.music.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // 登录接口
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        User user = userService.findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return "Login success";
        }
        return "Invalid credentials";
    }

//    // 获取当前登录用户信息
//    @GetMapping("/me")
//    public User getCurrentUser(HttpSession session) {
//        return (User) session.getAttribute("user");
//    }

    // 登出
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out";
    }
}
