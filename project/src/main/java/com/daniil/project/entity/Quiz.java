package com.daniil.project.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes")
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

    public Quiz() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
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
