package com.daniil.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "quizzes")
@Setter @Getter @NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private int id;

    @Column(name = "quiz_name")
    private String quizName;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Score> scoreList;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questionList;

    public Quiz(String quizName) {
        this.quizName = quizName;
    }

    public void addScore(Score score){
        if(this.scoreList == null){
            scoreList = new ArrayList<>();
        }

        scoreList.add(score);
    }

    public void addQuestion(Question question){
        if(this.questionList == null){
            questionList = new ArrayList<>();
        }

        questionList.add(question);
    }
}
