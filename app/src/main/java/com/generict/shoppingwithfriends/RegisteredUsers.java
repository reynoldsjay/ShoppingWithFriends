package com.generict.shoppingwithfriends;
import java.util.ArrayList;
import java.util.Hashtable;

public class RegisteredUsers {

    public static Hashtable<String,String> regUsers = new Hashtable<String,String>();
    public static ArrayList<User> users = new ArrayList<>();

    public static void add(User user){
        regUsers.put(user.getUsername(), user.getPassword());
        users.add(user);
    }

    public static boolean contains(User user) {
        return regUsers.containsKey(user.getUsername());
    }

    public static boolean checkValid(User user){
        return user.getPassword().equals(regUsers.get(user.getUsername()));
    }

}