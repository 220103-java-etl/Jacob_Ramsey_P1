package com.revature;

import com.revature.models.*;
import com.revature.repositories.ReimbursementRequestDOA;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementRequestService;
import com.revature.services.UserService;
import com.revature.util.FrontEndClass;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        ReimbursementRequestDOA r=new ReimbursementRequestDOA();
        ReimbursementRequestService rs=new ReimbursementRequestService();
        UserDAO userDAO=new UserDAO();
        UserService userService=new UserService();
        //rs.updateReimReqTimeing();
        rs.reimbursementCreate(userDAO.getByUserId(14).get(),new ReimbursementRequest(EventType.OTHER,"gdfdf","dfdsf",userDAO.getByUserId(14).get(),"2022-01-28",BigDecimal.valueOf(100.34),Status.PENDING,Timeing.NORMAL));

        //FrontEndClass frontEndClass=new FrontEndClass(1);
        //UserDAO dao=new UserDAO();

        //System.out.println(r.reimbursementRequestGetAllRequest());






       }
    }


