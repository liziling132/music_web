package com.music.demo.dao;

import java.time.LocalDateTime;

public class PlaylistSong {
    private Long playlistId;
    private Long songId;
    private LocalDateTime addedAt;

    // Getters and Setters
    public Long getPlaylistId() { return playlistId; }
    public void setPlaylistId(Long playlistId) { this.playlistId = playlistId; }
    public Long getSongId() { return songId; }
    public void setSongId(Long songId) { this.songId = songId; }
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
}
