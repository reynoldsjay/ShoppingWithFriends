package com.generict.shopwithfriends;

/**
 * Created by vignesh on 1/29/15.
 */

public class User {

        private String username;
        private String password;

        User(String username, String password) {
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
            return ((username.equals("user")) && (password.equals("pass")));
        }
}

