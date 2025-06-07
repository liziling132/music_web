package com.music.demo.controller;

import com.music.demo.dao.Mv;
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

    @GetMapping("/recommend")
    public ResponseEntity<List<Mv>> getRecommendedMvs(
            @RequestParam(value = "categoryId", required = false, defaultValue = "1") Long categoryId) {
        List<Mv> recommendedMvs = mvService.getRecommendedMvsByCategoryId(categoryId);
        return recommendedMvs.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(recommendedMvs);
    }

    @GetMapping("/{id}")
    public Mv getMvById(@PathVariable Long id) {
        return mvService.getMvById(id);
    }


}
