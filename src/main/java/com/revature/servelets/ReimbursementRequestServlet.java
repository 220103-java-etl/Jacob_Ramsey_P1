package com.revature.servelets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ReimbursementRequest;
import com.revature.services.ReimbursementRequestService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReimbursementRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService u=new UserService();
        ReimbursementRequestService reimbursementService=new ReimbursementRequestService();
        StringBuilder uriString = new StringBuilder(req.getRequestURI());

        System.out.println(uriString);
        uriString.replace(0, req.getContextPath().length() +1, "");
        System.out.println(uriString);

        ObjectMapper Om= new ObjectMapper();
        int index=0;
        if(uriString.indexOf("/")==-1){
            List<ReimbursementRequest> reims=reimbursementService.getAllReimReq();
            String reimburses=Om.writeValueAsString(reims);
            resp.getWriter().write(reimburses);

        }
        else if(uriString.indexOf("/")!=-1){
             index= Integer.valueOf(uriString.replace(0, uriString.indexOf("/") + 1,"").toString());
             ReimbursementRequest reimReq=reimbursementService.getReimReqById(index).get();
             String reimAtId=Om.writeValueAsString(reimReq);
             resp.getWriter().write(reimAtId);
            System.out.println(index);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


}
