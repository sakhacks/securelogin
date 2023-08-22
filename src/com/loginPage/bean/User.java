package com.loginPage.bean;

import java.util.Date;

public class User {

    private String userName;
    private String password;
    private String emailId;
    private Date blockFromTime;
    private boolean lockStatus;
    private int attemptsNum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


}
