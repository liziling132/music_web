package com.music.demo.service;

import com.music.demo.dao.Category;
import com.music.demo.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getAllCategories() {
        return categoryMapper.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryMapper.findById(id);
    }

    public Category getCategoryByName(String name) {
        return categoryMapper.findByName(name);
    }
}
