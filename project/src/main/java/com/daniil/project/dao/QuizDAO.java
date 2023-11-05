package com.daniil.project.dao;

import com.daniil.project.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizDAO extends JpaRepository<Quiz, Integer> {

    @Query("SELECT q FROM Quiz q " +
                "JOIN Score s ON q.id = s.quiz.id " +
                "GROUP BY q.id " +
                "ORDER BY COUNT(s) DESC")
    List<Quiz> getFeaturedQuizzes(Pageable pageable);

    Page<Quiz> findAllByQuizNameContaining(String name, Pageable pageable);

}
