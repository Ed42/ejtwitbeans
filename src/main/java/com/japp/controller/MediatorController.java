package com.japp.controller;

import com.japp.domain.User;
import com.japp.repository.UserRepository;
import com.japp.repository.UserRepositoryImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MediatorController {

    private static org.slf4j.Logger sLogger = LoggerFactory.getLogger(MediatorController.class);
    
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
        sLogger.warn("MediatorController testDb() ");
        sLogger.warn(" MediatorController testDb() "+ userService.count());
        if (userService.count() == 0) {
            
         User u =   userService.save(new User("admin@admin.com", "adminuser", "adminpwd", User.Roles.ROLE_ADMIN));
         sLogger.warn(" MediatorController testDb() create admin user "+ u.getUsername());
         
        }
        
    
    }
}