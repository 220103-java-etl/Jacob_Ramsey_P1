package com.revature.servelets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementRequest;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.services.ReimbursementService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class ValidatedReimReq extends HttpServlet {
    ReimbursementService reimbursementService=new ReimbursementService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session=req.getSession(false);
        ObjectMapper objectMapper = new ObjectMapper();
         User u =(User)session.getAttribute("user");
        List<Reimbursement> reimbursemeenList=reimbursementService.getByReimbursementResolver(u.getId());
        System.out.println(reimbursemeenList);
        String responseBody=objectMapper.writeValueAsString(reimbursemeenList);
        resp.getWriter().print(responseBody);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        ObjectMapper objectMapper = new ObjectMapper();
        User u =(User)session.getAttribute("user");


        int id=Integer.valueOf(req.getParameter("ValFormId"));
        String grade=req.getParameter("Grade");

        reimbursementService.insertFinalGrade(grade,id);
        resp.setContentType("text/html");
        resp.getWriter().print("<h1> You've inserted the grade or presentation status for your reimbursement</h1><br>" + " " +
                "<p> Click the link to go back and <a href=http://localhost:8086/ERS/Employee.html>Employee Interface</a>");

    }

}
