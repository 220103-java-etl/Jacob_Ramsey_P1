package com.revature.services;

import com.revature.MockDb.Database;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementRequest;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReimbursementRequestService {
    ReimbursementDAO reimbursementDAO=new ReimbursementDAO();
   public void reimbursementCreate(User u,ReimbursementRequest r){

       reimbursementDAO.reimbursementRequestCreate(u,r);
    }
}
