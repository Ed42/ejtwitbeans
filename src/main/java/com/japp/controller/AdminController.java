package com.japp.controller;

import com.japp.App;
import com.japp.domain.User;
import com.japp.domain.dto.UserDto;
import com.japp.domain.dto.UserListDto;
import com.japp.domain.dto.UserMapper;
import com.japp.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.management.relation.Role;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {

   private static org.slf4j.Logger sLogger = LoggerFactory.getLogger(AdminController.class);
   
   
    @Autowired
    private UserRepository service;

    @RequestMapping
    public String getUsersPage() {
        return "admin";
    }

    @RequestMapping(value = "/records")
    public @ResponseBody
    UserListDto getUsers() {

        UserListDto userListDto = new UserListDto();
        EndResult<User> list = service.findAll();
        List<User> users = new ArrayList<User>();
        for (User r : list) {
            users.add(r);
        }
        sLogger.warn("!!!!!!!!!AdminController: "+users.size());
        userListDto.setUsers(UserMapper.map(users));
        return userListDto;
    }

    @RequestMapping(value = "/get")
    public @ResponseBody
    User get(@RequestBody User user) {
        return service.findByUsername(user.getUsername());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    UserDto create(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam Integer role) {
        sLogger.warn("UserController: create..");
        sLogger.warn("username:" + username);

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);

        newUser.setRole(UserMapper.mapRoles(role));

        return UserMapper.map(service.create(newUser));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    UserDto update(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam Integer role) {

        User existingUser = new User();
        existingUser.setUsername(username);
        existingUser.setPassword(password);
        existingUser.setEmail(email);

        existingUser.setRole(UserMapper.mapRoles(role));
        return UserMapper.map(service.update(existingUser));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Boolean delete(
            @RequestParam String username) {

        User existingUser = new User();
        existingUser.setUsername(username);

        return service.deleteExistingUser(existingUser);
    }
}
