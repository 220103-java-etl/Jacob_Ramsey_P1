package com.revature;

import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.*;
import com.revature.services.*;
import com.revature.services.ReimbursementService;

public class Driver {

    public static void main(String[] args) {
        UserDAO userDAO=new UserDAO();
        AuthService authService=new AuthService();
        ReimbursementRequestService r=new ReimbursementRequestService();
        System.out.println(r.getById(32));




       }
    }


