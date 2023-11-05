package com.daniil.project.dao;

import com.daniil.project.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicDAO extends JpaRepository<Topic, Integer> {
}
