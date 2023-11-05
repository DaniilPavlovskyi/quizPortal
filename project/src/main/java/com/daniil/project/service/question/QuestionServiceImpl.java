package com.daniil.project.service.question;

import com.daniil.project.dao.QuestionDAO;
import com.daniil.project.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAO questionDAO;

    public QuestionServiceImpl(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @Override
    public List<Question> getQuestionsForQuiz(int id) {
        return questionDAO.findAllByQuizId(id);
    }

    @Override
    public List<Question> findAllQuestions() {
        return questionDAO.findAll();
    }
}
