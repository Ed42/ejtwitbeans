package com.japp.domain.service;

import com.japp.domain.User;
import com.japp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.stereotype.Service;

@Service
public class InitialiseDatabase {

    @Autowired
    private UserRepository userRepository;


    public void populateDatabase() {
    User user = userRepository.save(new User("admin@admin.com", "admin", "admin", User.Roles.ROLE_ADMIN));

    }
}
