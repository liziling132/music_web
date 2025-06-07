package com.music.demo.service;

import com.music.demo.dao.Song;
import com.music.demo.mapper.SongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongMapper songMapper;


    public Song getSongById(Long id) {
        return songMapper.findById(id);
    }

    public List<Song> getAllSongs() {
        return songMapper.findAll();
    }

    public List<Song> getRecommendedSongsByCategoryId(Long categoryId) {
        return songMapper.findRecommendedSongsByCategoryId(categoryId);
    }
}