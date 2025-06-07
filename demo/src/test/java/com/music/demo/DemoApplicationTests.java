package com.music.demo;

import com.music.demo.dao.Playlist;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class DemoApplicationTests {


	public static void main(String[] args) {
		Playlist playlist = new Playlist();
		playlist.setCreatedAt(LocalDateTime.now()); // 看是否报错
	}


}
