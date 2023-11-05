package com.daniil.project.service.quiz;

import com.daniil.project.entity.Quiz;
import org.springframework.data.domain.Page;


import java.util.List;

public interface QuizService {

    Page<Quiz> findAllQuizzes(int page, int size);

    Page<Quiz> findQuizzesByName(int page, int size,String name);

    Quiz findQuizById(int id);

    List<Quiz> getFeaturedQuizzes();

    void save(Quiz quiz);
}
