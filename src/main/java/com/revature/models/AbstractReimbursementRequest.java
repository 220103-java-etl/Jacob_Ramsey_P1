package com.revature.models;

import java.sql.Date;
import java.util.*;



public class AbstractReimbursementRequest {
    private Status status;
    private Timeing timeing;
    private double reimbursmentAmount;
    private Map<EventType,Double> reimbursmentRub;
    private EventType eventType;
    private String gradeingFormat;
    private String locAndWorkRelationDoc;
    private Date currentDate;
    private User u;

    private Date dateOfEvent;




    public  AbstractReimbursementRequest(EventType eventType, String gradeingFormat, String locAndWorkRelationDoc, User user, String eventDate,double totalCost,
                                         Status status, Timeing timeing){


        this.reimbursmentAmount=totalCost;
        this.currentDate=new java.sql.Date(System.currentTimeMillis());
        this.dateOfEvent=java.sql.Date.valueOf(eventDate);
        this.locAndWorkRelationDoc=locAndWorkRelationDoc;
        this.eventType=eventType;
        this.gradeingFormat=gradeingFormat;
        this.u=user;
        this.status=status;
        this.timeing=timeing;

}

    public Status getStatus() {
        return status;
    }

    public Timeing getTimeing() {
        return timeing;
    }

    public double getReimbursmentAmount() {
        return reimbursmentAmount;
    }

    public void setReimbursmentAmount(double reimbursmentAmount) {
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
                "reimbursmentAmount=" + reimbursmentAmount +
                ", eventType=" + eventType +
                ", gradeingFormat='" + gradeingFormat + '\'' +
                ", standIndocProof='" + locAndWorkRelationDoc + '\'' +
                ", currentDate=" + currentDate +
                ", user=" + u +
                ", dateOfEvent=" + dateOfEvent+
                '}';
    }
}
