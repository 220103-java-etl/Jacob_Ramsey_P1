package com.revature.servelets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.*;
import com.revature.services.ReimbursementRequestService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class ReimbursementRequestServlet extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();
    UserService userService=new UserService();
    ReimbursementRequestService reimbursementService=new ReimbursementRequestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserService userService=new UserService();
        ReimbursementRequestService reimbursementService=new ReimbursementRequestService();
        StringBuilder uriString = new StringBuilder(req.getRequestURI());

        HttpSession session = req.getSession(false);
        User user = (User)session.getAttribute("user");
        System.out.println(user);
       List<ReimbursementRequest> r= reimbursementService.getReimbursementReq(user);
        System.out.println(r);
        String responseBody = objectMapper.writeValueAsString(r);
        resp.setContentType("application/json");
        resp.getWriter().print(responseBody);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReimbursementRequestService rs=new ReimbursementRequestService();
        HttpSession session = req.getSession(false);

        ReimbursementRequest reimbursementRequest= new ReimbursementRequest(EventType.valueOf(req.getParameter("eventtype")),
                req.getParameter("gradeingformat"),req.getParameter("detaildoc"),(User)session.getAttribute("user"),req.getParameter("eventdate"),
                BigDecimal.valueOf(Double.valueOf(req.getParameter("totalcost"))), Status.PENDING, Timeing.NORMAL,req.getParameter("location"));
        reimbursementService.reimbursementCreate((User)session.getAttribute("user"),reimbursementRequest);

    }


}
