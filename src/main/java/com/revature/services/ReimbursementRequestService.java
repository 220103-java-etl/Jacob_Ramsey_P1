package com.revature.services;

import com.revature.models.ReimbursementRequest;
import com.revature.models.Status;
import com.revature.models.Timeing;
import com.revature.models.User;
import com.revature.repositories.ReimbursementRequestDOA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                System.out.println((r.getDateOfEvent().getTime()/86400000)-(current.getTime()/86400000));
                reimbursementDAO.updateReimRequestTimeing(Timeing.URGENT,r.getId());
            }
        }

    }

    public void updateReimRequestValidatyService(Status status, int userFormId){
       reimbursementDAO.updateReimRequestValidaty(status, userFormId);
        }
    }

