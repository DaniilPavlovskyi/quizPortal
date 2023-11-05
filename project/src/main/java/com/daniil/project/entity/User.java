package com.daniil.project.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "enabled")
    private int enabled;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<Response> responseList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Score> scoreList;

    public User(String username, int enabled) {
        this.username = username;
        this.enabled = enabled;
    }


    public User() {
    }


    public void addResponse(Response response) {
        if (responseList == null) {
            responseList = new ArrayList<>();
        }
        responseList.add(response);
    }

    public List<Response> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<Response> responseList) {
        this.responseList = responseList;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    public void addScore(Score score){
        if(this.scoreList == null){
            scoreList = new ArrayList<>();
        }

        scoreList.add(score);
    }


}
