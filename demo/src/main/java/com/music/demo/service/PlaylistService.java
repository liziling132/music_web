package com.music.demo.service;

import com.music.demo.dao.Playlist;
import com.music.demo.dao.PlaylistSong;
import com.music.demo.dao.Song;
import com.music.demo.mapper.PlaylistMapper;
import com.music.demo.mapper.PlaylistSongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistMapper playlistMapper;

    @Autowired
    private PlaylistSongMapper playlistSongMapper;

    public List<Playlist> getUserPlaylists(Long userId) {
        return playlistMapper.findByUserId(userId);
    }

    public void createPlaylist(Long userId, String name) {
        Playlist playlist = new Playlist();
        playlist.setUserId(userId);
        playlist.setName(name);
        playlist.setCreatedAt(LocalDateTime.now());
        playlistMapper.insert(playlist);
    }

    @Transactional
    public void addSongToPlaylist(Long playlistId, Long songId) {
        PlaylistSong playlistSong = new PlaylistSong();
        playlistSong.setPlaylistId(playlistId);
        playlistSong.setSongId(songId);
        playlistSong.setAddedAt(LocalDateTime.now());
        playlistSongMapper.insert(playlistSong);
    }

    public void deletePlaylist(Long id) {
        playlistMapper.delete(id);
    }
}