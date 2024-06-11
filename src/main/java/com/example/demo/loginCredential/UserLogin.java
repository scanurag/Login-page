package com.example.demo.loginCredential;

public class UserLogin {

    private String userEmail;
    private String userPassword;

    // Getters and setters

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "UserLogin [userEmail=" + userEmail + ", userPassword=" + userPassword + "]";
    }
}
