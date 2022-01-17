package com.revature.models;

public class ReimbursementRequest extends AbstractReimbursementRequest{
    public ReimbursementRequest(EventType eventType, String gradeingFormat,String standIndocProof, User user, int day, int month, int year, double totalCost) {

        super(eventType,gradeingFormat,standIndocProof,user,day,month,year,totalCost);

    }


}
