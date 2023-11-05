package com.daniil.project.service.quiz;

import com.daniil.project.dao.QuizDAO;
import com.daniil.project.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizDAO quizDAO;

    @Autowired
    public QuizServiceImpl(QuizDAO quizDAO) {
        this.quizDAO = quizDAO;
    }

    @Override
    public Page<Quiz> findAllQuizzes(int page, int size) {
        return quizDAO.findAll(PageRequest.of(page, size));
    }

    public Page<Quiz> findQuizzesByName(int page, int size,String name) {
        return quizDAO.findAllByQuizNameContaining(name, PageRequest.of(page, size));
    }
    @Override
    public Quiz findQuizById(int id) {
        return quizDAO.findById(id).orElse(null);
    }

    @Override
    public List<Quiz> getFeaturedQuizzes() {
        return quizDAO.getFeaturedQuizzes(PageRequest.of(0, 3));
    }

    @Override
    public void save(Quiz quiz) {
        quizDAO.save(quiz);
    }
}
