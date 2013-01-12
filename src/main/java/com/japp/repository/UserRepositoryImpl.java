package com.japp.repository;

import com.japp.domain.User;
import com.japp.domain.service.EtwitterUserDetails;
import com.japp.domain.service.EtwitterUserDetailsService;
import com.japp.util.Logging;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class UserRepositoryImpl implements EtwitterUserDetailsService {

    @Autowired
    private Neo4jOperations template;
    private static org.slf4j.Logger sLogger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Override
    public EtwitterUserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        final User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }
        return new EtwitterUserDetails(user);
    }

    @Override
    public User getUserFromSession() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof EtwitterUserDetails) {
            EtwitterUserDetails userDetails = (EtwitterUserDetails) principal;
            return userDetails.getUser();
        }
        return null;
    }

    @Override
    @Transactional
    public User register(String email, String username, String password) {
        User found = findByUsername(username);
        if (found != null) {
            throw new RuntimeException("Login already taken: " + username);
        }
        if (username == null || username.isEmpty()) {
            throw new RuntimeException("No name provided.");
        }
        if (password == null || password.isEmpty()) {
            throw new RuntimeException("No password provided.");
        }
        User user = template.save(new User(email, username, password, User.Roles.ROLE_USER));
        setUserInSession(user);
        return user;
    }

    void setUserInSession(User user) {
        SecurityContext context = SecurityContextHolder.getContext();
        EtwitterUserDetails userDetails = new EtwitterUserDetails(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
        context.setAuthentication(authentication);

    }

    @Transactional
    private User findByUsername(String username) {

        EndResult<User> list = template.findAll(User.class);
        List<User> users = new ArrayList<User>();
        for (User r : list) {
            if (r.getUsername().equals(username)) {
                return r;
            }
        }
        return new User("null user", "null user", "null user", User.Roles.ROLE_USER);

    }

    @Transactional
    public User create(User user) {
        User existingUser = findByUsername(user.getUsername());
        if (existingUser.getEmail().equals("null user")) {
            return template.save(user);
        }
        return existingUser;
    }

    @Transactional
    public User update(User user, String origName) {
        User existingUser = findByUsername(origName);

        if (existingUser == null) {
            return null;
        }

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole()[0]);

        return template.save(existingUser);
    }

    @Transactional
    public Boolean deleteExistingUser(String username) {
        User u = findByUsername(username);
        if (u.getUsername().equals("null user")) {
            return false;
        } else {
            template.delete(u);
        }
        return true;
    }
}
