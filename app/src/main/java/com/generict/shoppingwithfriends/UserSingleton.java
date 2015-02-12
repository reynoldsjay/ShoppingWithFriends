package com.generict.shoppingwithfriends;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by JennaKwon on 2/11/15.
 * Credit: Robert Waters, CS2340.simplelistviewdemo
 */
public class UserSingleton {

    private static UserSingleton instance = new UserSingleton();
    List<User> users = new ArrayList<>();

    /**
     * Method for returning one instance of this class
     * @return one instance of this class
     */
    public static UserSingleton getInstance() { return instance; }

    /**
     * private constructor for generating the model
     */
    private UserSingleton( ) {makeModel(); }

    /**
     * a method that adds a hard-coded user list to the instance variable
     * User constructor used: String name, String email, int rating, int numPostings)
     */
    private void makeModel() {
        users.add(new User("Jenna Kwon", "jinkwon06@gmail.com", 10, 45));
        users.add(new User("Biggy Prasad", "getbig@gmail.com", 12, 63));
        users.add(new User("Mar DeSouza", "marmarland@gmail.com", 9, 69));
        users.add(new User("Bae Reynolds", "jaybay@gmail.com", 6, 30));
        users.add(new User("Brandy Brand", "brandy@gmail.com", 17, 50));
        users.add(new User("John Smith", "johnsmoth@gmail.com", 45, 150));
        users.add(new User("Roger Federer", "rogerfed@gmail.com", 50, 1230));
        users.add(new User("Rafael Nadal", "rafa@gmail.com", 2, 12));
    }

    /**
     * Returns a list of users
     * @return list of users
     */
    public List<User> getUsers() { return users;}
}