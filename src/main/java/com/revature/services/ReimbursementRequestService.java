package com.revature.services;

import com.revature.MockDb.Database;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementRequest;
import com.revature.models.Timeing;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.ReimbursementRequestDOA;

import java.util.*;

public class ReimbursementRequestService {
    ReimbursementRequestDOA reimbursementDAO = new ReimbursementRequestDOA();

    public void reimbursementCreate(User u, ReimbursementRequest r) {
        reimbursementDAO.reimbursementRequestCreate(r, u);
    }

    public List<ReimbursementRequest> getReimbursementReq(User u) {
        return reimbursementDAO.reimbursementRequestGetByUserName(u);
    }

    public void updateReimReqTimeing() {
        List<ReimbursementRequest> arrayList=reimbursementDAO.reimbursementRequestGetAllRequest();
        Date current=new java.sql.Date(System.currentTimeMillis());
        for(ReimbursementRequest r:arrayList){
            if((r.getDateOfEvent().getTime()/86400000)-(current.getTime()/86400000)<=14){
                reimbursementDAO.updateReimRequest(Timeing.URGENT,r.getUser().getId());
            }
        }

    }
}


