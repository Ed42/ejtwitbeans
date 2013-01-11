package com.japp.domain.dto;

import com.japp.controller.AdminController;
import java.util.ArrayList;
import java.util.List;

import com.japp.domain.User;
import com.japp.domain.dto.UserDto;
import org.slf4j.LoggerFactory;

public class UserMapper {
    
        private static org.slf4j.Logger sLogger = LoggerFactory.getLogger(UserMapper.class);

	public static UserDto map(User user) {
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setEmail(user.getEmail());
			dto.setUsername(user.getUsername());
			dto.setPassword(user.getPassword());
			dto.setRole(mapRole(user.getRole()[0]));
         sLogger.warn("!!!!!!!!!UserMapper returning a dto with dto.getUsername: "+dto.getUsername());
			return dto;
	}
	
        public static int mapRole(User.Roles role) {
            
            if (role == User.Roles.ROLE_USER) return 2;
            else return 1;
            
        }
        
        
            public static User.Roles[] mapRoles(int role) {
            User.Roles[] roles = new User.Roles[2];
            if (role == 2) roles[0]=User.Roles.ROLE_USER;
            else roles[0]=User.Roles.ROLE_ADMIN;
            return roles;
        }
        
        
        
        
	public static List<UserDto> map(List<User> users) {
		List<UserDto> dtos = new ArrayList<UserDto>();
		for (User user: users) {
			dtos.add(map(user));
		}
                 sLogger.warn("!!!!!!!!!UserMapper returning a dto list size : "+dtos.size());
		return dtos;
	}
        
        
}
