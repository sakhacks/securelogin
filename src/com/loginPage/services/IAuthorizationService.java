package com.loginPage.services;

import com.loginPage.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface IAuthorizationService {
    public void loginService(User user, HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws IOException;
    public void registerService(User user, HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws IOException;
    public void logoutService(HttpSession session, HttpServletRequest req, HttpServletResponse resp);
}
