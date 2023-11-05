package com.daniil.project.form;

import java.util.ArrayList;
import java.util.List;

public class QuizForm {

    private String quizName;
    private int selectedTopicId;
    private List<QuestionForm> questions = new ArrayList<>();

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public int getSelectedTopicId() {
        return selectedTopicId;
    }

    public void setSelectedTopicId(int selectedTopicId) {
        this.selectedTopicId = selectedTopicId;
    }

    public List<QuestionForm> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionForm> questions) {
        this.questions = questions;
    }
}
