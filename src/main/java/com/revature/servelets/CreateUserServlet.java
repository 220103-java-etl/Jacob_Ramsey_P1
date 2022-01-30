package com.revature.servelets;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class CreateUserServlet extends HttpServlet {
    AuthService aS=new AuthService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("CreateUserForm.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    BigDecimal b=new BigDecimal(1000);
        BigDecimal a=new BigDecimal(0);
        HttpSession session = req.getSession();
      String userName=  req.getParameter("username");
      String passWord=req.getParameter("password");
      String confirmPass=  req.getParameter("confirmpass");
      String firstName=req.getParameter("firstname");
      String lastName=req.getParameter("lastname");
      String email=req.getParameter("email");
        Role userRole=Role.valueOf(req.getParameter("role"));

        if(userRole.equals(Role.EMPLOYEE)) {
            User u = new User(userName, passWord, userRole, b,firstName,lastName,email);
            aS.register(u);
            session.setAttribute("user",u);
        }else{
            User u = new User(userName, passWord, userRole, a,firstName,lastName,email);
            aS.register(u);
            session.setAttribute("user",u);
        }
    }
}
