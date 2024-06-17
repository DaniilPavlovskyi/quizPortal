package com.daniil.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_options")
@Getter @Setter @NoArgsConstructor
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private int id;

    @Column(name = "option_text")
    private String text;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "is_correct")
    private int isCorrect;

    @OneToMany(mappedBy = "option",
            cascade = CascadeType.ALL)
    private List<Response> responseList;

    public Option(String text, int isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public void addResponse(Response response) {
        if (this.responseList == null) {
            responseList = new ArrayList<>();
        }

        responseList.add(response);
    }
}
