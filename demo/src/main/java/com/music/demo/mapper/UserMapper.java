package com.music.demo.mapper;

import com.music.demo.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User findById(Long id);
    void updateUser(User user);
    User findByUsername(String username);
}
