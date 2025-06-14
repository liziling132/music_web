package com.music.demo.service;

import com.music.demo.dao.User;
import com.music.demo.mapper.UserMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Cacheable(value = "user", key = "#userId")
    public User getUserProfile(Long userId) {
        System.out.println("Querying database for user: " + userId); // 明确是否每次都进方法
        return userMapper.findById(userId);
    }

    public void updateUserProfile(User user) {
        userMapper.updateUser(user);
    }

    @Cacheable(value = "user", key = "#username")
    public User findUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public void registerUser(User user) {
        user.setPassword(user.getPassword());

        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        userMapper.insertUser(user);
    }
}
