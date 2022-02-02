package com.revature.servelets;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserLoginServlet extends HttpServlet {
    AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("LoginFile.html");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            HttpSession session = req.getSession();

            User u = authService.login(username, password);

            session.setAttribute("user", u);
            if (u.getRole().equals(Role.EMPLOYEE)) {
                resp.sendRedirect("Employee.html");
            } else {
                resp.sendRedirect("FinanceManager.html");
            }


        } catch (Exception e) {
            String exception = e.getMessage();
            resp.setContentType("text/html");
            resp.getWriter().print("<h1>" + exception + "</h1><br>" + " " +
                    "<p> Click the link to go back and <a href='http://localhost:8086/ERS/LoginFile.html'>Login</a>");
        }
    }
}
