package com.japp.domain.dto;

import java.io.Serializable;
import java.util.List;

public class UserListDto implements Serializable {

    private List<UserDto> users;

    public UserListDto() {
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
