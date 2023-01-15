package com.example.xpenseapp;

public class Users {
    private String email;
    private  String name;

    public Users() {
    }

    public Users(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
