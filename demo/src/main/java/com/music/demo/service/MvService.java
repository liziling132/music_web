package com.music.demo.service;

import com.music.demo.dao.Mv;
import com.music.demo.mapper.MvMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MvService {
    @Autowired
    private MvMapper mvMapper;

    public List<Mv> getRecommendedMvsByCategoryId(Long categoryId) {
        return mvMapper.findRecommendedMvsByCategoryId(categoryId);
    }

    public Mv getMvById(Long id) {
        return mvMapper.findById(id);
    }
}