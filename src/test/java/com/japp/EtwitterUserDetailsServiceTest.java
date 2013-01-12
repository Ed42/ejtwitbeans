package com.japp;

import com.japp.controller.AdminController;
import com.japp.domain.User;
import com.japp.domain.dto.UserDto;
import com.japp.domain.dto.UserMapper;
import com.japp.repository.UserRepository;
import com.japp.util.Logging;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EtwitterUserDetailsServiceTest {

    private static org.slf4j.Logger sLogger = LoggerFactory.getLogger(EtwitterUserDetailsServiceTest.class);
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    private Neo4jOperations template;

    @Test
    public void saveUserTest() {
        User user = userRepository.save(new User("admin@admin.com", "admin", "admin", User.Roles.ROLE_USER));
        assertEquals("user email", "admin@admin.com", user.getEmail());

        assertNotNull("user roles", user.getRole());
        assertEquals("user role", User.Roles.ROLE_USER, user.getRole()[0]);
        User user2 = userRepository.findOne(user.getId());
        assertEquals("loaded user id", user.getId(), user2.getId());
        assertEquals("loaded user Username", "admin", user2.getUsername());
        assertNotNull("loaded user roles", user2.getRole());
        assertEquals("loaded user role ", User.Roles.ROLE_USER, user2.getRole()[0]);
    }

    @Test
    public void userRepoTest() {
        sLogger.warn("EtwitterUserDetailsServiceTest: userRepoTest");

        User user = userRepository.save(new User("admin@admin.com", "admin", "admin", User.Roles.ROLE_USER));

        EndResult<User> list = template.findAll(User.class);
        List<User> users = new ArrayList<User>();
        for (User r : list) {
            if (r.getUsername().equals("admin")) {
                sLogger.warn("EtwitterUserDetailsServiceTest: userRepoTest found" + r.getEmail());
            }
        }
        User user2 = new User("null user", "null user", "null user", User.Roles.ROLE_USER);
        if (user2.getEmail().equals("null user")) {
            sLogger.warn("EtwitterUserDetailsServiceTest: userRepoTest found" + user2.getEmail());
        }

        User user3 = template.save(user2);

        sLogger.warn("EtwitterUserDetailsServiceTest: userRepoTest found" + user3.getEmail());

        EndResult<User> list1 = template.findAll(User.class);
        while (list1.iterator().hasNext()) {
            User u = list1.iterator().next();
            sLogger.warn("EtwitterUserDetailsServiceTest: userRepoTest found: " + u.getUsername());
        }
        sLogger.warn("EtwitterUserDetailsServiceTest: userRepoTest delete");
        template.delete(user3);
        EndResult<User> list2 = template.findAll(User.class);
        while (list2.iterator().hasNext()) {
            User u = list2.iterator().next();
            sLogger.warn("EtwitterUserDetailsServiceTest: userRepoTest found: " + u.getUsername());
        }
        sLogger.warn("EtwitterUserDetailsServiceTest: userRepoTest update user");
        sLogger.warn("user: " + user.getUsername() + " ; " + user.getEmail());
        user.setUsername("userRepoTest");
        user.setEmail("userRepoTest email");
        user.setRole(user.getRole()[0]);

        User user4 =  template.save(user);
        
        sLogger.warn("EtwitterUserDetailsServiceTest: userRepoTest updated user");
        sLogger.warn("user now : " + user4.getUsername() + " ; " + user4.getEmail());

    }
}
