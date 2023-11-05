package com.daniil.project.service.topic;

import com.daniil.project.entity.Topic;

import java.util.List;

public interface TopicService {

    List<Topic> getAllTopics();

    Topic findTopicById(int selectedTopicId);
}
