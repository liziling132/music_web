package com.music.demo.mapper;

import com.music.demo.dao.PlaylistSong;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface PlaylistSongMapper {
    int insert(PlaylistSong playlistSong);
}
