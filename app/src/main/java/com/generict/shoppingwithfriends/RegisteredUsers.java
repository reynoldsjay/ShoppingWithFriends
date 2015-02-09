package com.generict.shoppingwithfriends;
import java.util.ArrayList;
import java.util.Hashtable;
/**
 * Keeps track of all registered users with static collections
 * @author Jay
 * @version 1.0
 */
public class RegisteredUsers {

    public static Hashtable<String,String> regUsers = new Hashtable<String,String>();
    public static ArrayList<User> users = new ArrayList<>();

    /**
     * Adds a new user to static collections
     * @param user user to be added
     */
    public static void add(User user){
        regUsers.put(user.getUsername(), user.getPassword());
        users.add(user);
    }

    /**
     * Checks if a username is in use
     * @param user user to be checked
     * @return true if username is taken
     */
    public static boolean contains(User user) {
        return regUsers.containsKey(user.getUsername());
    }

    /**
     * Checks if a user is valid
     * @param user user to be checked
     * @return true if username and password match
     */
    public static boolean checkValid(User user){
        return user.getPassword().equals(regUsers.get(user.getUsername()));
    }

}