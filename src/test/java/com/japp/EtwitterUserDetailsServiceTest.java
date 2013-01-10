package com.japp;

import com.japp.domain.User;
import com.japp.repository.UserRepository;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EtwitterUserDetailsServiceTest {

    @Autowired
    protected UserRepository userRepository;
    @Autowired
    private Neo4jTemplate template;

    @Test
    public void saveUserTest() {
         User user = userRepository.save(new User("admin@admin.com", "admin", "admin", User.Roles.ROLE_USER));
         assertEquals("user email", "admin@admin.com",user.getEmail());
        
        assertNotNull("user roles", user.getRole());
        assertEquals("user role", User.Roles.ROLE_USER,user.getRole()[0]);
        User user2=userRepository.findOne(user.getId());
        assertEquals("loaded user id", user.getId(),user2.getId());
        assertEquals("loaded user Username", "admin",user2.getUsername());
        assertNotNull("loaded user roles", user2.getRole());
        assertEquals("loaded user role ", User.Roles.ROLE_USER, user2.getRole()[0]); 
    }
}
