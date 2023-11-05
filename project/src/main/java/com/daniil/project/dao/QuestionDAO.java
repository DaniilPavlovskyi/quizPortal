package com.daniil.project.dao;

import com.daniil.project.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionDAO extends JpaRepository<Question, Integer> {

    List<Question> findAllByQuizId(int id);
}
