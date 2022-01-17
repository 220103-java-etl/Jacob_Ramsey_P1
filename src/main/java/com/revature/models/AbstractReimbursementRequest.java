package com.revature.models;

import java.util.*;



public class AbstractReimbursementRequest {

    private double reimbursmentAmount;
    private Map<EventType,Double> reimbursmentRub;
    private EventType eventType;
    private String gradeingFormat;
    private String standIndocProof;
    private Calendar currentDate;
    private User user;
    private String gradeRecieved;
    private Calendar dateOfEvent=new Calendar() {
    @Override
    protected void computeTime() {

    }

    @Override
    protected void computeFields() {

    }

    @Override
    public void add(int field, int amount) {

    }

    @Override
    public void roll(int field, boolean up) {

    }

    @Override
    public int getMinimum(int field) {
        return 0;
    }

    @Override
    public int getMaximum(int field) {
        return 0;
    }

    @Override
    public int getGreatestMinimum(int field) {
        return 0;
    }

    @Override
    public int getLeastMaximum(int field) {
        return 0;
    }
};




    public  AbstractReimbursementRequest(EventType eventType, String gradeingFormat, String standIndocProof, User user,int day,int month, int year,double totalCost){

        reimbursmentRub= Map.of(EventType.CERTIFICATION,1.0,EventType.CERTIFICATION_PREP_CLASS,
                .75,EventType.SEMINAR,.60,EventType.OTHER,.30,EventType.TECHNICAL_TRAINING,.90,EventType.UNIVERSITY_COURSE,.80);
        this.reimbursmentAmount=totalCost*reimbursmentRub.get(eventType);
        this.currentDate=new GregorianCalendar();
        this.dateOfEvent=new GregorianCalendar(day,month,year);
        this.standIndocProof=standIndocProof;
        this.eventType=eventType;
        this.gradeingFormat=gradeingFormat;
        this.user=user;

}
    public String getGradeRecieved() {
        return gradeRecieved;
    }

    public void setGradeRecieved(String gradeRecieved) {
        this.gradeRecieved = gradeRecieved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return standIndocProof;
    }

    public void setStandIndocProof(String standIndocProof) {
        this.standIndocProof = standIndocProof;
    }

    public Calendar getDateOfEvent() {
        return dateOfEvent;
    }

    public Calendar getCurrentDate() {
        return currentDate;
    }

    @Override
    public String toString() {
        return "AbstractReimbursementRequest{" +
                "reimbursmentAmount=" + reimbursmentAmount +
                ", eventType=" + eventType +
                ", gradeingFormat='" + gradeingFormat + '\'' +
                ", standIndocProof='" + standIndocProof + '\'' +
                ", currentDate=" + currentDate.get(Calendar.MONTH) +", "+currentDate.get(Calendar.DAY_OF_MONTH)+", "+currentDate.get(Calendar.YEAR) +
                ", user=" + user +
                ", gradeRecieved='" + gradeRecieved + '\'' +
                ", dateOfEvent=" + dateOfEvent.get(Calendar.MONTH) +", "+dateOfEvent.get(Calendar.DAY_OF_MONTH)+", "+dateOfEvent.get(Calendar.YEAR)+
                '}';
    }
}
