package com.loginPage.model;

import com.loginPage.bean.User;

public interface ICommonRepository {

    public String checkUser(String email, String password);
    public boolean createUser(String userName, String email, String password);
    public User getUserDetails(String email,String password);
}
