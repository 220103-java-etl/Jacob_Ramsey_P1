package com.revature.services;

import com.revature.models.ReimbursementRequest;
import com.revature.models.Status;
import com.revature.models.Timeing;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.ReimbursementRequestDOA;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class ReimbursementRequestService {
    ReimbursementRequestDOA reimbursementDAO = new ReimbursementRequestDOA();
    ReimbursementService reimbursement=new ReimbursementService();
    public Optional<ReimbursementRequest> getReimReqById(int Id){
        return reimbursementDAO.reimbursementRequestGetById(Id);
    }
    public void reimbursementCreate(User u, ReimbursementRequest r) {
        BigDecimal b = u.getAvailableReimbursement();
        if (b.equals(BigDecimal.ZERO)) {
            System.out.println("Sorry you have no more available reimbursement left");
        } else if (b.subtract(r.getReimbursmentAmount()).compareTo(BigDecimal.ZERO) < 0) {
            r.setReimbursmentAmount(b);
            reimbursementDAO.reimbursementRequestCreate(r, u);
        } else {
            System.out.println(b.subtract(r.getReimbursmentAmount()).doubleValue());
            reimbursementDAO.reimbursementRequestCreate(r, u);
        }
    }

    public ReimbursementRequest getById(int Id){
        return reimbursementDAO.reimbursementRequestGetById(Id).get();
    }

    public List<ReimbursementRequest> getReimbursementReq(User u) {
        return reimbursementDAO.reimbursementRequestGetByUserName(u);
    }

    public void updateReimReqTimeing() {
        List<ReimbursementRequest> arrayList=reimbursementDAO.reimbursementRequestGetAllOpenRequest();
        Date current=new java.sql.Date(System.currentTimeMillis());
        for(ReimbursementRequest r:arrayList){
            if((r.getDateOfEvent().getTime()/86400000)-(current.getTime()/86400000)<=14){

                reimbursementDAO.updateReimRequestTimeing(Timeing.URGENT,r.getId());
            }
        }

    }

    public void updateReimRequestValidatyService(Status status, int userFormId,User userUpdateing)throws SQLException{
        if(Status.DENIED.equals(status)) {
            reimbursementDAO.updateReimRequestValidaty(status, userFormId);
        }
        else{
            reimbursement.addReimbusementService(userFormId,userUpdateing);
            reimbursementDAO.updateReimRequestValidaty(status, userFormId);
        }

    }

    public void deleteReimRequest(int userFormId){
        reimbursementDAO.deleteReimbursementRequest(userFormId);
    }

    public List<ReimbursementRequest> getAllReimReq(){
        return reimbursementDAO.reimbursementRequestGetAllOpenRequest();
    }

    public BigDecimal updateReimRequestAmount(int Id,BigDecimal b){
        return reimbursementDAO.updateReimRequestAmount(b,Id);
    }

    public String updateReimRequestRelatedDoc(int Formid,String newInfo){
        return reimbursementDAO.updateReimRequestInfo(newInfo,Formid);
    }

    public String getAccessOfReqForm(int formId){
        return reimbursementDAO.getAccessValueForRequestById(formId);
    }
    public void updateAccessToForm(String s,int formId){
        reimbursementDAO.updateFormAccess(s,formId);
    }
}


