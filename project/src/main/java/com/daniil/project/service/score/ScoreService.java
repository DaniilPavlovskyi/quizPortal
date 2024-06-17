package com.daniil.project.service.score;

import com.daniil.project.entity.Score;
import org.springframework.data.domain.Page;

public interface ScoreService {
    void save(Score score);

    Page<Score> getUserScoresForQuiz(String username, int id, int page, int size);

    Page<Score> getUserScores(String username, int page, int size);

    int getTotalScore();
}
