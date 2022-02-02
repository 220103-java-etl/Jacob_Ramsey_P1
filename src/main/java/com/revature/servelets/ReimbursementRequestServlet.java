package com.revature.servelets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.exceptions.UserNameException;
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
    UserService userService = new UserService();
    ReimbursementRequestService reimbursementService = new ReimbursementRequestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserService userService = new UserService();
        ReimbursementRequestService reimbursementService = new ReimbursementRequestService();
        StringBuilder uriString = new StringBuilder(req.getRequestURI());

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        List<ReimbursementRequest> r = reimbursementService.getReimbursementReq(user);
        System.out.println(r);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String responseBody = objectMapper.writeValueAsString(r);
        resp.setContentType("application/json");
        resp.getWriter().print(responseBody);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
        HttpSession session = req.getSession(false);
        User currentUser = (User) session.getAttribute("user");
        if (req.getParameter("eventtype") != null) {

            ReimbursementRequest reimbursementRequest = new ReimbursementRequest(EventType.valueOf(req.getParameter("eventtype")),
                    req.getParameter("gradeingformat"), req.getParameter("detaildoc"), (User) session.getAttribute("user"), req.getParameter("eventdate"),
                    BigDecimal.valueOf(Double.valueOf(req.getParameter("totalcost"))), Status.PENDING, Timeing.NORMAL, req.getParameter("location"));
            if((reimbursementRequest.getDateOfEvent().getTime()/86400000)-(reimbursementRequest.getCurrentDate().getTime()/86400000)<=7)
            {
                resp.sendRedirect("/ERS/ReimReq.html");
            }
            else {
                reimbursementService.reimbursementCreate((User) session.getAttribute("user"), reimbursementRequest);
            }
        } else if (req.getParameter("updateInfo") != null) {

            boolean reimReqOwnership = false;

            String updateInfo = req.getParameter("updateInfo");
            int formid = Integer.valueOf(req.getParameter("formId"));
            if (reimbursementService.getAccessOfReqForm(formid).equals("True")) {
                List<ReimbursementRequest> currentUserReims = reimbursementService.getReimbursementReq(currentUser);

                for (ReimbursementRequest r : currentUserReims) {
                    if (r.getUser().equals(currentUser)) {
                        reimReqOwnership = true;
                    }
                }
                if (reimReqOwnership == true) {
                    reimbursementService.updateReimRequestRelatedDoc(formid, updateInfo);

                }

            } else {
                UserNameException userNameException = new UserNameException("You dont have permission to access this form");
                throw userNameException;
            }

        } else if (req.getParameter("formIdDelete") != null) {

            int deleteForm = Integer.valueOf(req.getParameter("formIdDelete"));
            String access = reimbursementService.getAccessOfReqForm(deleteForm);
           ReimbursementRequest r = reimbursementService.getReimReqById(deleteForm).get();
            if (access.equals("Delete") && r.getUser().getId() == currentUser.getId()) {
                reimbursementService.deleteReimRequest(deleteForm);

            } else {
                UserNameException exception = new UserNameException("You do not have permission to delete");
                throw exception;
            }

        }


    } catch(UserNameException u){
            String exception = u.getMessage();
            resp.setContentType("text/html");
            resp.getWriter().print("<h1>" + exception + "</h1><br>" + " " +
                    "<p> Click the link to go back and <a href='http://localhost:8086/ERS/LoginFile.html'>Login</a>");
        }catch(Exception e){
            resp.setContentType("text/html");
            resp.getWriter().print("<h1> Whoops something went wrong!</h1><br>" + " " +
                    "<p> Click the link to go back and <a href=http://localhost:8086/ERS/Employee.html>Employee Interface</a>");
        }
    }
}
