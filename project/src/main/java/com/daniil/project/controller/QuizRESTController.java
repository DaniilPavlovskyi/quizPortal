package com.daniil.project.controller;

import com.daniil.project.service.score.ScoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class QuizRESTController {

    private final ScoreService scoreService;

    @GetMapping("/api/global-score")
    public ResponseEntity<Integer> getGlobalScore() {
        return new ResponseEntity<>(scoreService.getTotalScore(), HttpStatus.OK);
    }
}
