package com.daniil.project.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_questions")
@Getter @Setter @NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int id;

    @Column(name = "question_text")
    private String text;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
    private List<Option> optionList;

    @OneToMany(mappedBy = "question",
            cascade = CascadeType.ALL)
    private List<Response> responseList;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Question(String text) {
        this.text = text;
    }

    public void addOption(Option option) {
        if (this.optionList == null) {
            optionList = new ArrayList<>();
        }

        optionList.add(option);
    }

    public void addResponse(Response response) {
        if (this.responseList == null) {
            responseList = new ArrayList<>();
        }

        responseList.add(response);
    }

}
