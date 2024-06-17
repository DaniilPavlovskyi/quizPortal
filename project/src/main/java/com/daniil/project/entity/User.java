package com.daniil.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<Response> responseList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Score> scoreList;

    public User(String username, String password, int enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public void addResponse(Response response) {
        if (responseList == null) {
            responseList = new ArrayList<>();
        }
        responseList.add(response);
    }

    public void addScore(Score score){
        if(this.scoreList == null){
            scoreList = new ArrayList<>();
        }

        scoreList.add(score);
    }
}
