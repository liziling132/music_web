package com.music.demo.controller;

import com.music.demo.dao.Song;
import com.music.demo.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/song")
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/recommend")
    public ResponseEntity<List<Song>> getRecommendedSongs(
            @RequestParam(value = "categoryId", required = false, defaultValue = "1") Long categoryId) {
        List<Song> recommendedSongs = songService.getRecommendedSongsByCategoryId(categoryId);
        return recommendedSongs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(recommendedSongs);
    }


    @GetMapping("/{id}")
    public Song getSongById(@PathVariable Long id) {
        return songService.getSongById(id);
    }

    @GetMapping("/list")
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }
}