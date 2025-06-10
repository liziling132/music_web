package com.music.demo.controller;

import com.music.demo.dao.Song;
import com.music.demo.result.Result;
import com.music.demo.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/song")
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/recommend/{categoryId}")
    public Result<List<Song>> getRecommendedSongs(@PathVariable Long categoryId) {
        List<Song> songs = songService.getRecommendedSongsByCategoryId(categoryId);

        if (songs.isEmpty()) {
            return Result.error(404, "没有推荐歌曲");
        }

        return Result.success(songs);
    }

    @GetMapping("/{id}")
    public Result<Song> getSongById(@PathVariable Long id) {
        Song song = songService.getSongById(id);

        if (song == null) {
            return Result.error(404, "歌曲不存在");
        }

        return Result.success(song);
    }

    @GetMapping("/list")
    public Result<List<Song>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();

        if (songs.isEmpty()) {
            return Result.error(404, "暂无歌曲");
        }

        return Result.success(songs);
    }
}