
package com.music.demo.service;

import com.music.demo.dao.User;
import com.music.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserMapper userMapper;

    // 构造器注入
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getUserProfile(Long userId) {
        return userMapper.findById(userId);
    }

    public void updateUserProfile(User user) {
        userMapper.updateUser(user);
    }

    public User findUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public void registerUser(User user) {
        // 直接保存明文密码
        user.setPassword(user.getPassword());

        // 设置创建和更新时间
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        userMapper.insertUser(user);
    }
}
