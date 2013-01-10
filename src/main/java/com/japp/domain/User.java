package com.japp.domain;

import org.springframework.data.neo4j.annotation.*;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;


@NodeEntity
public class User {
    @GraphId Long nodeId;

    private static final String SALT = "cewuiqwzie";

    @Indexed
    String username;
    String email;
    String password;
    private Roles[] roles;

    public User() {
    }

    public User(String email, String username, String password, Roles... roles) {
        this.email = email;
        this.username = username;
        this.password = encode(password);
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    private String encode(String password) {
        return new Md5PasswordEncoder().encodePassword(password, SALT);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", username, email);
    }

    public Roles[] getRole() {
        return roles;
    }

    public void setRole(Roles[] roles) {
		this.roles = roles;
	}
    
    
    public void updatePassword(String old, String newPass1, String newPass2) {
        if (!password.equals(encode(old))) throw new IllegalArgumentException("Existing Password invalid");
        if (!newPass1.equals(newPass2)) throw new IllegalArgumentException("New Passwords don't match");
        this.password = encode(newPass1);
    }


    public enum Roles implements GrantedAuthority {
        ROLE_USER, ROLE_ADMIN;

        @Override
        public String getAuthority() {
            return name();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if (nodeId == null) return super.equals(o);
        return nodeId.equals(user.nodeId);

    }

    public Long getId() {
        return nodeId;
    }

    @Override
    public int hashCode() {

        return nodeId != null ? nodeId.hashCode() : super.hashCode();
    }
}
