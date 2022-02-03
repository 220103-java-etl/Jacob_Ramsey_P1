package com.revature.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;



public class AbstractReimbursementRequest {
    private Status status;
    private Timeing timeing;
    private BigDecimal reimbursmentAmount;
    private Map<EventType,Double> reimbursmentRub;
    private EventType eventType;
    private String gradeingFormat;
    private String locAndWorkRelationDoc;
    private Date currentDate;
    private User u;
    private int id;
    private String location;
    private Date dateOfEvent;




    public  AbstractReimbursementRequest(int id,EventType eventType, String gradeingFormat, String locAndWorkRelationDoc, User user, String eventDate,BigDecimal totalCost,
                                         Status status, Timeing timeing,String location){

        this.id=id;
        this.reimbursmentAmount=totalCost;
        this.currentDate=new java.sql.Date(System.currentTimeMillis());
        this.dateOfEvent=java.sql.Date.valueOf(eventDate);
        this.locAndWorkRelationDoc=locAndWorkRelationDoc;
        this.eventType=eventType;
        this.gradeingFormat=gradeingFormat;
        this.u=user;
        this.status=status;
        this.timeing=timeing;
        this.location=location;

}
    public  AbstractReimbursementRequest(EventType eventType, String gradeingFormat, String locAndWorkRelationDoc, User user, String eventDate,BigDecimal totalCost,
                                         Status status, Timeing timeing,String location){


        this.reimbursmentAmount=totalCost;
        this.currentDate=new java.sql.Date(System.currentTimeMillis());
        this.dateOfEvent=java.sql.Date.valueOf(eventDate);
        this.locAndWorkRelationDoc=locAndWorkRelationDoc;
        this.eventType=eventType;
        this.gradeingFormat=gradeingFormat;
        this.u=user;
        this.status=status;
        this.timeing=timeing;
        this.location=location;

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public Timeing getTimeing() {
        return timeing;
    }

    public BigDecimal getReimbursmentAmount() {
        return reimbursmentAmount;
    }

    public void setReimbursmentAmount(BigDecimal reimbursmentAmount) {
        this.reimbursmentAmount = reimbursmentAmount;
    }



    public User getUser() {
        return u;
    }

    public void setUser(User u) {
        this.u = u;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }



    public String getGradeingFormat() {
        return gradeingFormat;
    }

    public void setGradeingFormat(String gradeingFormat) {
        this.gradeingFormat = gradeingFormat;
    }

    public String getStandIndocProof() {
        return locAndWorkRelationDoc;
    }

    public void setStandIndocProof(String standIndocProof) {
        this.locAndWorkRelationDoc = standIndocProof;
    }

    public Date getDateOfEvent() {
        return dateOfEvent;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    @Override
    public String toString() {
        return "AbstractReimbursementRequest{" +
                "status=" + status +
                ", timeing=" + timeing +
                ", reimbursmentAmount=" + reimbursmentAmount +
                ", reimbursmentRub=" + reimbursmentRub +
                ", eventType=" + eventType +
                ", gradeingFormat='" + gradeingFormat + '\'' +
                ", locAndWorkRelationDoc='" + locAndWorkRelationDoc + '\'' +
                ", currentDate=" + currentDate +
                ", u=" + u +
                ", id=" + id +
                ", location='" + location + '\'' +
                ", dateOfEvent=" + dateOfEvent +
                '}';
    }
}
