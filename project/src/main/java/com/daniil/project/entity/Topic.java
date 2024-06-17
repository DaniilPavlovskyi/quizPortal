package com.daniil.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_topics")
@Getter @Setter
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private int id;

    @Column(name = "topic_name")
    private String topicName;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Quiz> quizList;

    public void addQuiz(Quiz quiz){
        if(this.quizList == null){
            this.quizList = new ArrayList<>();
        }

        quizList.add(quiz);
    }


}
