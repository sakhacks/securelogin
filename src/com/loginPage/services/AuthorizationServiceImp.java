package com.loginPage.services;

import com.loginPage.bean.User;
import com.loginPage.model.CommonRepositoryImp;
import com.loginPage.model.ICommonRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.Logger;

public class AuthorizationServiceImp implements IAuthorizationService {

    private static final Logger logger = Logger.getLogger(CommonRepositoryImp.class.getName());
    @Override
    public void loginService(User user,HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws IOException {

        logger.info("LoginService is called.");
        ICommonRepository DB = new CommonRepositoryImp();
        String status = DB.checkUser(user.getEmailId(), user.getPassword());

        switch(status)
        {
            case "Present":
                session.setAttribute("status","Present");
                User userDetails = DB.getUserDetails(user.getEmailId(), user.getPassword());
                req.setAttribute("details",userDetails);
                RequestDispatcher RD = req.getRequestDispatcher("index.jsp");

                try {
                    RD.forward(req,resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case "Block":
                resp.sendRedirect("login.jsp?error=blocked");

                break;
            case "Invalid1":
                resp.sendRedirect("login.jsp?error=invalid&attempts=1");
                break;
            case "Invalid2":
                resp.sendRedirect("login.jsp?error=invalid&attempts=2");
                break;
            case "Not Present":
                resp.sendRedirect("login.jsp?error=NotPresent");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + status);
        }

    }

    @Override
    public void registerService(User user,HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws IOException {

        logger.info("RegisterService is called.");
        ICommonRepository DB = new CommonRepositoryImp();
        boolean status = DB.createUser(user.getUserName(), user.getEmailId(), user.getPassword());
        logger.info("Register service status success:"+status);
        if(status)
        {
            resp.sendRedirect("login.jsp?success=1");
        }else
        {
            resp.sendRedirect("login.jsp?success=0");
        }
    }

    @Override
    public void logoutService(HttpSession session, HttpServletRequest req, HttpServletResponse resp) {

        logger.info("LogoutService is called.");
        session.setAttribute("status","Logout");
        logger.info("Setting session attribute status as Logout.");
        RequestDispatcher RD = req.getRequestDispatcher("login.jsp");

        try {
            RD.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
