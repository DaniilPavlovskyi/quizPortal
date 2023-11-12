package com.daniil.project.service.user;

import com.daniil.project.entity.User;


public interface UserService {
    User findByUsername(String username);

    void save(User user);

 }
