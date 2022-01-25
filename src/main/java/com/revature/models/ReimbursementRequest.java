package com.revature.models;

public class ReimbursementRequest extends AbstractReimbursementRequest{
    public ReimbursementRequest(int id,EventType eventType, String gradeingFormat,String standIndocProof, User u,String eventDate, double totalCost,Status status,Timeing timeing) {

        super(id, eventType,gradeingFormat,standIndocProof,u,eventDate,totalCost,status,timeing);

    }
    public  ReimbursementRequest(EventType eventType, String gradeingFormat, String locAndWorkRelationDoc, User user, String eventDate,double totalCost,
                                         Status status, Timeing timeing){
        super( eventType,gradeingFormat,locAndWorkRelationDoc,user,eventDate,totalCost,status,timeing);

    }

}
