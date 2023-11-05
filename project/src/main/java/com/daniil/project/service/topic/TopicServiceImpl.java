package com.daniil.project.service.topic;

import com.daniil.project.dao.TopicDAO;
import com.daniil.project.entity.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService{

    private final TopicDAO topicDAO;

    public TopicServiceImpl(TopicDAO topicDAO) {
        this.topicDAO = topicDAO;
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicDAO.findAll();
    }

    @Override
    public Topic findTopicById(int selectedTopicId) {
        return topicDAO.findById(selectedTopicId).orElse(null);
    }
}
