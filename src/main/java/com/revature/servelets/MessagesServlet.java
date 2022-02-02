package com.revature.servelets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.UserNameException;
import com.revature.models.ReimbursementRequest;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.MessageService;
import com.revature.services.ReimbursementRequestService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

public class MessagesServlet extends HttpServlet {
    MessageService messageService=new MessageService();
    ReimbursementRequestService reimbursementRequestService=new ReimbursementRequestService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpSession session= req.getSession(false);
        User current=(User)session.getAttribute("user");
        messageService.getMessages(current.getId());
        List<String> messages= messageService.getMessages(current.getId());
        String responseBody = objectMapper.writeValueAsString(messages);
        resp.setContentType("application/json");
        resp.getWriter().print(responseBody);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            User currentUser = (User) session.getAttribute("user");
            if (currentUser.getRole().equals(Role.EMPLOYEE)) {
                UserNameException userNameException = new UserNameException("You are not authorized");
                throw userNameException;
            }

            int formId = Integer.valueOf(req.getParameter("formid"));
            ReimbursementRequest currentForm = reimbursementRequestService.getById(formId);

            String message = currentUser.getfName() + " " + currentUser.getlName() + " is requesting :" + req.getParameter("additionalrequest") +
                    " for Reimbursement form event happeining on the " + currentForm.getDateOfEvent() + " with the Id of: " + currentForm.getId();
            messageService.sendMessageToUser(currentUser.getId(), currentForm.getUser().getId(), message);
            reimbursementRequestService.updateAccessToForm("True", currentForm.getId());


        }
        catch(UserNameException u){
            String exception = u.getMessage();
            resp.setContentType("text/html");
            resp.getWriter().print("<h1>"+ exception +"</h1><br>" + " " +
                    "<p> Click the link to go back and <a href=http://localhost:8086/ERS/LoginFile.html >Login</a>");
        }
        catch (Exception e){
            String exception = e.getMessage();
            resp.setContentType("text/html");
            resp.getWriter().print("<h1>Something went wrong</h1><br>" + " " +
                    "<p> Click the link to go back and <a href=http://localhost:8086/ERS/FinanceManager.html>Finance Manager Interface</a>");
        }
    }
}
