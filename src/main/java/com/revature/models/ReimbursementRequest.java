package com.revature.models;

public class ReimbursementRequest extends AbstractReimbursementRequest{
    public ReimbursementRequest(EventType eventType, String gradeingFormat,String standIndocProof, User u,String eventDate, double totalCost,Status status,Timeing timeing) {

        super(eventType,gradeingFormat,standIndocProof,u,eventDate,totalCost,status,timeing);

    }


}
