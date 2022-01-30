package com.revature.servelets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ReimbursementRequest;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.services.ReimbursementRequestService;
import com.revature.services.UserService;
import com.revature.repositories.ReimbursementRequestDOA;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class FinanceManagerReimReqCont extends HttpServlet {
    ReimbursementRequestService reimbursementRequestService=new ReimbursementRequestService();
    UserService userService=new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserService userService=new UserService();
        ReimbursementRequestService reimbursementService=new ReimbursementRequestService();
        StringBuilder uriString = new StringBuilder(req.getRequestURI());

        HttpSession session = req.getSession(false);


        List<ReimbursementRequest> r= reimbursementService.getAllReimReq();
        System.out.println(r);
        String responseBody = objectMapper.writeValueAsString(r);
        resp.setContentType("application/json");
        resp.getWriter().print(responseBody);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if(req.getParameter("validate or invalidate")!=null) {
            int formId = Integer.valueOf(req.getParameter("reimformid"));
            Status status = Status.valueOf(req.getParameter("validate or invalidate"));
            User approver = (User) session.getAttribute("user");
            reimbursementRequestService.updateReimRequestValidatyService(status, formId, approver);
        }else if(req.getParameter("reimformamount")!=null){
            int formId = Integer.valueOf(req.getParameter("reimformId"));
            BigDecimal reimAvailAmount = BigDecimal.valueOf(Double.valueOf(req.getParameter("reimformamount")));

            ReimbursementRequest r= reimbursementRequestService.getById(formId);

            BigDecimal updateUserReimAvail=r.getReimbursmentAmount().subtract(reimAvailAmount);
            System.out.println(updateUserReimAvail.doubleValue());
            User u=(r.getUser());
            System.out.println(u.getAvailableReimbursement().doubleValue());

            userService.updateAvailableReimbursement(u,updateUserReimAvail.negate());
            reimbursementRequestService.updateReimRequestAmount(formId,reimAvailAmount);

        }


    }
}
