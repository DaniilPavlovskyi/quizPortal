package com.daniil.project.service.score;

import com.daniil.project.dao.ScoreDAO;
import com.daniil.project.entity.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService{

    private final ScoreDAO scoreDAO;

    public ScoreServiceImpl(ScoreDAO scoreDAO) {
        this.scoreDAO = scoreDAO;
    }

    @Override
    public void save(Score score) {
        scoreDAO.save(score);
    }

    @Override
    public Page<Score> getUserScoresForQuiz(String username, int id, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return scoreDAO.findAllByUserUsernameAndQuizId(username, id, pageable);
    }

    @Override
    public Page<Score> getUserScores(String username, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return scoreDAO.findAllByUserUsername(username, pageable);
    }
}
