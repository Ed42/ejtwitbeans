package com.japp.controller;

import com.japp.domain.User;
import com.japp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MediatorController {

  @Autowired
    private UserRepository userService;

    @RequestMapping
    public String getHomePage() {
        testDb();
        return "index";
    }

    @RequestMapping(value = "/auth/denied", method = RequestMethod.GET)
    public String profile(Model model) {


        return "/auth/deniedpage";
 
    
    }

    private void testDb() {
    User user = userService.save(new User("admin@admin.com", "adminuser", "adminpwd", User.Roles.ROLE_ADMIN));
    
    }
}