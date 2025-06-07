package com.music.demo.controller;

import com.music.demo.dao.Playlist;
import com.music.demo.dao.Song;
import com.music.demo.service.PlaylistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    // 只有登录后才能访问
    @GetMapping("/my")
    public List<Playlist> getUserPlaylists(HttpSession session) {
        if (session.getAttribute("user") == null) {
            throw new RuntimeException("Unauthorized");
        }
        Long userId = ((com.music.demo.dao.User) session.getAttribute("user")).getId();
        return playlistService.getUserPlaylists(userId);
    }

    // 创建歌单
    @PostMapping
    public void createPlaylist(@RequestParam String name, HttpSession session) {
        if (session.getAttribute("user") == null) {
            throw new RuntimeException("Unauthorized");
        }
        Long userId = ((com.music.demo.dao.User) session.getAttribute("user")).getId();
        playlistService.createPlaylist(userId, name);
    }

    // 添加歌曲到歌单
    @PostMapping("/{id}/add-song")
    public void addSongToPlaylist(@PathVariable Long id,
                                  @RequestParam Long songId,
                                  HttpSession session) {
        if (session.getAttribute("user") == null) {
            throw new RuntimeException("Unauthorized");
        }
        playlistService.addSongToPlaylist(id, songId);
    }

    // 删除歌单
    @DeleteMapping("/{id}")
    public void deletePlaylist(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("user") == null) {
            throw new RuntimeException("Unauthorized");
        }
        playlistService.deletePlaylist(id);
    }
}
