package com.daniil.project.dao;

import com.daniil.project.entity.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreDAO extends JpaRepository<Score, Integer> {

    Page<Score> findAllByUserUsername(String username, Pageable pageable);
    Page<Score> findAllByUserUsernameAndQuizId(String username, int quizId, Pageable pageable);

}
