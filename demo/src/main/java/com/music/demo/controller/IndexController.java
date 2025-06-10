package com.music.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "forward:/index.html";  //  index.html 是入口文件
    }

    @GetMapping("/**")
    public String fallback() {
        return "forward:/index.html";  // Vue 路由刷新
    }
}
