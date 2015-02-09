package com.generict.shoppingwithfriends;

/**
 * Created by vignesh on 1/29/15.
 */

public class User {

    private String name;
    private String email;
    private String username;
    private String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return RegisteredUsers.checkValid(this);
    }

}