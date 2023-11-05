package com.daniil.project.form;

import java.util.ArrayList;
import java.util.List;

public class QuestionForm {

    private String text;
    private List<OptionForm> options = new ArrayList<>();

    private int correctOptionIndex;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<OptionForm> getOptions() {
        return options;
    }

    public void setOptions(List<OptionForm> options) {
        this.options = options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public void setCorrectOptionIndex(int correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }

}