package com.daniil.project.form;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class QuestionForm {
    private String text;
    private List<OptionForm> options = new ArrayList<>();
    private int correctOptionIndex;
}