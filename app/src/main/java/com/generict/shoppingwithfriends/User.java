package com.generict.shoppingwithfriends;

/**
 * Class for a user
 * @author Vignesh
 * @version 1.0
 */



public class User {

    private String email;
    private String username;
    private String password;
    private String name;
    private int rating;
    private int numPostings;

    /**
     * Makes a user with just username and password. Used for validating user at the login screen.
     *
     * @param name name of user
     * @param username username of user
     * @param password password of user
     */
    User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /**
     * Makes a new user with all information.
     *
     * @param name name of user
     * @param email email of user
     * @param username username of user
     * @param password password of user
     */
    User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * Makes a new user for FriendsListActivity
     *
     * @param name name of user
     * @param email email of user
     * @param rating username of user
     * @param numPostings password of user
     */
    User(String name, String email, int rating, int numPostings) {
        this.name = name;
        this.email = email;
        this.rating = rating;
        this.numPostings = numPostings;
    }

    /**
     * Gets username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets username.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if user is valid.
     * @return true if user is valid
     */
    public boolean isValid() {
        return RegisteredUsers.checkValid(this);
    }

    /**
     * Gets name
     * @return
     */
    public String getName() {return name;}

    /**
     * Sets name
     * @param name
     */
    public void setName(String name) {this.name = name;}
}
