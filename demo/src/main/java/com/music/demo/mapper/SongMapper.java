package com.music.demo.mapper;

import com.music.demo.dao.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SongMapper {

    Song findById(Long id);
    List<Song> findAll();
    List<Song> findRecommendedSongsByCategoryId(@Param("categoryId") Long categoryId);
}