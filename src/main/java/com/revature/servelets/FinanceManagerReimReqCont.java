package com.revature.servelets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.exceptions.UserNameException;
import com.revature.models.ReimbursementRequest;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.services.MessageService;
import com.revature.services.ReimbursementRequestService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import com.revature.repositories.ReimbursementRequestDOA;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class FinanceManagerReimReqCont extends HttpServlet {
    ReimbursementService reimbursementService = new ReimbursementService();
    ReimbursementRequestService reimbursementRequestService = new ReimbursementRequestService();
    UserService userService = new UserService();
    MessageService messageService = new MessageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            User approver = (User) session.getAttribute("user");
            if (approver.getRole().equals(Role.EMPLOYEE)) {
                UserNameException userNameException = new UserNameException("You are not authorized");
                throw userNameException;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            UserService userService = new UserService();
            ReimbursementRequestService reimbursementService = new ReimbursementRequestService();
            StringBuilder uriString = new StringBuilder(req.getRequestURI());


            List<ReimbursementRequest> r = reimbursementService.getAllReimReq();
            System.out.println(r);

            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
           String responseBody=objectMapper.writeValueAsString(r);
            System.out.println(responseBody);
            resp.setContentType("application/json");
            resp.getWriter().print(responseBody);
        } catch (Exception e) {
            String exception = e.getMessage();
            resp.setContentType("text/html");
            resp.getWriter().print("<h1>" + exception + "</h1><br>" + " " +
                    "<p> Click the link to go back and <a href='http://localhost:8086/ERS/LoginFile.html'>Login</a>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(false);
            User approver = (User) session.getAttribute("user");
            if (approver.getRole().equals(Role.EMPLOYEE)) {
                UserNameException userNameException = new UserNameException("You are not authorized");
                throw userNameException;
            }
            if (req.getParameter("validate or invalidate") != null) {
                int formId = Integer.valueOf(req.getParameter("reimformid"));
                Status status = Status.valueOf(req.getParameter("validate or invalidate"));

                reimbursementRequestService.updateReimRequestValidatyService(status, formId, approver);
            } else if (req.getParameter("reimformamount") != null) {
                int formId = Integer.valueOf(req.getParameter("reimformId"));
                BigDecimal reimAvailAmount = BigDecimal.valueOf(Double.valueOf(req.getParameter("reimformamount")));

                ReimbursementRequest r = reimbursementRequestService.getById(formId);

                BigDecimal updateUserReimAvail = r.getReimbursmentAmount().subtract(reimAvailAmount);

                if (updateUserReimAvail.movePointRight(2).equals(BigDecimal.ZERO)) {

                    User u = r.getUser();


                    userService.updateAvailableReimbursement(u, updateUserReimAvail.negate());
                    reimbursementRequestService.updateReimRequestAmount(formId, reimAvailAmount);
                } else {

                    User changingReimUser = (User) session.getAttribute("user");
                    User u = r.getUser();
                    String message = "Your reimbursement request amount with the form id: " + r.getId() + " has been changed from "
                            + r.getReimbursmentAmount() + " to " + reimAvailAmount;
                    BigDecimal currentAvailableReim = u.getAvailableReimbursement();

                    if (currentAvailableReim.add(updateUserReimAvail).doubleValue() < 0.0) {
                        reimbursementRequestService.updateReimRequestValidatyService(Status.OVER_AVAILABLE_LIMIT, r.getId(), approver);
                    }

                    messageService.sendMessageToUser(changingReimUser.getId(), u.getId(), message);
                    System.out.println(u.getAvailableReimbursement().doubleValue());

                    userService.updateAvailableReimbursement(u, updateUserReimAvail.negate());
                    reimbursementRequestService.updateReimRequestAmount(formId, reimAvailAmount);
                    reimbursementRequestService.updateAccessToForm("Delete", formId);
                }
            } else if (req.getParameter("results") != null) {
                int formId = Integer.valueOf(req.getParameter("reimformid"));
                String results = req.getParameter("results");
                reimbursementService.updateGrade(results, formId);

                if (results.equals("Passed")) {
                    reimbursementService.updateStatus(Status.APPROVED, formId);
                } else {
                    reimbursementService.updateStatus(Status.DENIED, formId);
                }

            }


        }


        catch (UserNameException e) {
            String exception = e.getMessage();
            resp.setContentType("text/html");
            resp.getWriter().print("<h1>" + exception + "</h1><br>" + " " +
                    "<p> Click the link to go back and <a href='http://localhost:8086/ERS/LoginFile.html'>Login</a>");
        }
        catch (SQLException ex){

            resp.setContentType("text/html");
            resp.getWriter().print("<h1> If the form you requested to update exist it has been updated</h1><br>" + " " +
                    "<p> Click the link to go back and <a href='http://localhost:8086/ERS/FinanceManager.html'>Finance Manager Interface</a>");
        }
    }
}
