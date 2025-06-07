package com.music.demo.mapper;

import com.music.demo.dao.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {
    List<Category> findAll();

    Category findById(Long id);

    Category findByName(String name);
}