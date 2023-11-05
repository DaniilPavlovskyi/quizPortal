package com.daniil.project.service.question;

import com.daniil.project.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionsForQuiz(int id);

    List<Question> findAllQuestions();
}
