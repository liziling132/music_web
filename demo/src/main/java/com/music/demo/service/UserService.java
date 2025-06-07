package com.music.demo.service;

import com.music.demo.dao.User;
import com.music.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

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
}
