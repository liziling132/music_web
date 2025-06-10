package com.music.demo.controller;

import com.music.demo.dao.User;
import com.music.demo.result.Result;
import com.music.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session) {
        User user = userService.findUserByUsername(username);

        if (user == null) {
            return Result.error(401, "用户不存在");
        }

        if (!user.getPassword().equals(password)) {
            return Result.error(401, "密码错误");
        }

        session.setAttribute("user", user);
        return Result.success("登录成功");
    }
}
