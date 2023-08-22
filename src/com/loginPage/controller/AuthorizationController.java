package com.loginPage.controller;

import com.loginPage.bean.User;
import com.loginPage.model.CommonRepositoryImp;
import com.loginPage.services.AuthorizationServiceImp;
import com.loginPage.services.IAuthorizationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.apache.log4j.Logger;

import static org.apache.log4j.Logger.getLogger;

@WebServlet("/controller")
public class AuthorizationController extends HttpServlet {

    private static final Logger logger = getLogger(CommonRepositoryImp.class.getName());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("(AuthorizationController) is called.");
        HttpSession session = req.getSession();
        RequestDispatcher RD = null;
        IAuthorizationService service;
        service = new AuthorizationServiceImp();

        String requestedMethod =req.getParameter("serviceMethod");
        logger.info("FrontEnd requested for "+requestedMethod+" method from controller.");

        User user = new User();
        user.setUserName(req.getParameter("uname"));
        user.setEmailId(req.getParameter("userId"));
        user.setPassword(req.getParameter("password"));


        switch (requestedMethod)
        {
            case "login":
                service.loginService(user,session,req,resp);
                break;

            case "register":
                service.registerService(user,session,req,resp);
                break;

            case "logout":
                service.logoutService(session,req,resp);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + requestedMethod);
        }

    }
}
