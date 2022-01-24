package com.revature;

import com.revature.models.*;
import com.revature.repositories.ReimbursementRequestDOA;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementRequestService;
import com.revature.services.UserService;
import com.revature.util.FrontEndClass;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        ReimbursementRequestDOA r=new ReimbursementRequestDOA();
        ReimbursementRequestService rs=new ReimbursementRequestService();
        rs.updateReimReqTimeing();
        FrontEndClass frontEndClass=new FrontEndClass(1);
        //UserDAO dao=new UserDAO();

        //System.out.println(r.reimbursementRequestGetAllRequest());

      // System.out.println((R.getDateOfEvent().getTime()/86400000)-(R.getCurrentDate().getTime()/86400000));




       }
    }


