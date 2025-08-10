package com.academy.demo_ws.model;

public class FcmToken {
    private Long id;
    private String token;

    public FcmToken(Long id, String token) {
        this.id = id;
        this.token = token;
    }

    public FcmToken() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
