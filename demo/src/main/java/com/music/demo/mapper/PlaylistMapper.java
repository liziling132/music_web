package com.music.demo.mapper;

import com.music.demo.dao.Playlist;
import com.music.demo.dao.Song;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface PlaylistMapper {
    List<Playlist> findByUserId(Long userId);
    int insert(Playlist playlist);
    int delete(Long id);
}