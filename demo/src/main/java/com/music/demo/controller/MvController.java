package com.music.demo.controller;

import com.music.demo.dao.Mv;
import com.music.demo.result.Result;
import com.music.demo.service.MvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mv")
public class MvController {
    @Autowired
    private MvService mvService;

    @GetMapping("/recommend/{categoryId}")
    public Result<List<Mv>> getRecommendedMvs(@PathVariable Long categoryId) {
        List<Mv> mvs = mvService.getRecommendedMvsByCategoryId(categoryId);

        if (mvs.isEmpty()) {
            return Result.error(404, "没有推荐 MV");
        }

        return Result.success(mvs);
    }

    @GetMapping("/{id}")
    public Result<Mv> getMvById(@PathVariable Long id) {
        Mv mv = mvService.getMvById(id);

        if (mv == null) {
            return Result.error(404, "MV 不存在");
        }

        return Result.success(mv);
    }
}
