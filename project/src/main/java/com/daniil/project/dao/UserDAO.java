package com.daniil.project.dao;


import com.daniil.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDAO extends JpaRepository<User, String> {

    User findByUsername(String username);

}
