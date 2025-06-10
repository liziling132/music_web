package com.music.demo.controller;

import com.music.demo.dao.Playlist;
import com.music.demo.dao.Song;
import com.music.demo.dao.User;
import com.music.demo.result.Result;
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

    @GetMapping("/my")
    public Result<List<Playlist>> getUserPlaylists(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return Result.error(401, "请先登录");
        }

        Long userId = ((User) session.getAttribute("user")).getId();
        List<Playlist> playlists = playlistService.getUserPlaylists(userId);

        if (playlists.isEmpty()) {
            return Result.error(404, "你还没有创建歌单");
        }

        return Result.success(playlists);
    }

    @PostMapping
    public Result<Void> createPlaylist(@RequestParam String name, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return Result.error(401, "请先登录");
        }

        Long userId = ((User) session.getAttribute("user")).getId();
        playlistService.createPlaylist(userId, name);
        return Result.success(null);
    }

    @PostMapping("/{id}/add-song")
    public Result<Void> addSongToPlaylist(@PathVariable Long id,
                                          @RequestParam Long songId,
                                          HttpSession session) {
        if (session.getAttribute("user") == null) {
            return Result.error(401, "请先登录");
        }

        playlistService.addSongToPlaylist(id, songId);
        return Result.success(null);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deletePlaylist(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return Result.error(401, "请先登录");
        }

        playlistService.deletePlaylist(id);
        return Result.success(null);
    }
}

