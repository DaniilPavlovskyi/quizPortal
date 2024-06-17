package com.daniil.project.form;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class QuizForm {

    private String quizName;
    private int selectedTopicId;
    private List<QuestionForm> questions = new ArrayList<>();
}
