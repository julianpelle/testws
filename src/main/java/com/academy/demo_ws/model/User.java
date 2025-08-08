package com.academy.demo_ws.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode @ToString
public class User {
    private Long id;
    private String fullname;
    private String email;

    public User() {
    }

    public User(Long id, String fullname, String email) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
    }

    public User Id(Long id) {
        this.id = id;
        return this;
    }
    public User Fullname(String fullname) {
        this.fullname = fullname;
        return this;
    }
    public User Email(String email) {
        this.email = email;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
