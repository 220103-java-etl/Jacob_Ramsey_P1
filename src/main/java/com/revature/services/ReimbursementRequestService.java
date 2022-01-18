package com.revature.services;

import com.revature.MockDb.Database;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementRequest;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;

import java.util.*;

public class ReimbursementRequestService {
    ReimbursementDAO reimbursementDAO=new ReimbursementDAO();

    public void reimbursementCreate(User u,ReimbursementRequest r){
       if(reimbursementDAO.reimbursementGetExistStat(u)) {
           ArrayList<ReimbursementRequest> reimbursementRequests = new ArrayList<>();
           reimbursementRequests.add(r);
           reimbursementDAO.reimbursementRequestCreate(u, reimbursementRequests);
       }else{
           addReimbursement(u,r);
       }
    }
    public void addReimbursement(User u,ReimbursementRequest r){
    reimbursementDAO.addReimbusementRequest(u,r);

    }
}
