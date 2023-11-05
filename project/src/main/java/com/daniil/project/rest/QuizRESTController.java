package com.daniil.project.rest;


import com.daniil.project.entity.Question;
import com.daniil.project.service.quiz.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api")
public class QuizRESTController {

    private final QuizService quizService;

    @Autowired
    public QuizRESTController(QuizService quizService) {
        this.quizService = quizService;
    }

//    @GetMapping("/api/questions")
//    public List<Question> getAllQuestions(){
//        return quizService.findAllQuestions();
//    }

}
