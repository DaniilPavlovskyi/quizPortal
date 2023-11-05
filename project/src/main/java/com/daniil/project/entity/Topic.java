package com.daniil.project.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private int id;

    @Column(name = "topic_name")
    private String topicName;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Quiz> quizList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    public void addQuiz(Quiz quiz){
        if(this.quizList == null){
            this.quizList = new ArrayList<>();
        }

        quizList.add(quiz);
    }


}
