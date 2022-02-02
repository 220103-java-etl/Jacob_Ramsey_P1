package com.revature.models;

import java.math.BigDecimal;

public class ReimbursementRequest extends AbstractReimbursementRequest{
    public ReimbursementRequest(int id, EventType eventType, String gradeingFormat, String workRelatedDoc, User u, String eventDate, BigDecimal totalCost, Status status, Timeing timeing,String location) {

        super(id, eventType,gradeingFormat,workRelatedDoc,u,eventDate,totalCost,status,timeing,location);

    }
    public  ReimbursementRequest(EventType eventType, String gradeingFormat, String locAndWorkRelationDoc, User user, String eventDate,BigDecimal totalCost,
                                         Status status, Timeing timeing,String location){
        super( eventType,gradeingFormat,locAndWorkRelationDoc,user,eventDate,totalCost,status,timeing,location);

    }

}
