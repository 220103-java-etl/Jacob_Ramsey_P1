package com.revature.repositories;

import com.revature.models.*;
import com.revature.util.ConnectionFactory;

import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReimbursementRequestDOA {
    UserDAO userDAO=new UserDAO();
        ConnectionFactory cu=ConnectionFactory.getInstance();

    public void reimbursementRequestCreate(ReimbursementRequest reimbursementRequest,User u) {
        String sql = "insert into reimbursement_request values (default, ?, ?,?,?,?,?,?,?,?) returning *" ;

        try(Connection conn=cu.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, reimbursementRequest.getEventType().toString());
            ps.setString(2, reimbursementRequest.getGradeingFormat());
            ps.setString(3, reimbursementRequest.getStandIndocProof());
            ps.setInt(4, u.getId());
            ps.setDouble(5, reimbursementRequest.getReimbursmentAmount());
            ps.setDate(6, reimbursementRequest.getCurrentDate());
            ps.setDate(7,reimbursementRequest.getDateOfEvent());
            ps.setString(8,reimbursementRequest.getStatus().toString());
            ps.setString(9,reimbursementRequest.getTimeing().toString());
            ResultSet rs = ps.executeQuery();
            System.out.println("Reimbursment Created");



        }catch (SQLException s){
            s.printStackTrace();
        }


    }

    public List<ReimbursementRequest> reimbursementRequestGetByUserName(User u) {
        String sql = "select * from reimbursement_request where user_id=?" ;
        List<ReimbursementRequest> userReimRequest=new ArrayList<>();
        try(Connection conn=cu.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, u.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ReimbursementRequest r=new ReimbursementRequest(EventType.valueOf(rs.getString("event_type").toUpperCase(Locale.ROOT).replaceAll(" ","_")),
                        rs.getString("gradeing_format"),rs.getString("loc_work_rel_doc"),u,rs.getDate("event_dt").toString(),
                        rs.getDouble("reim_cost"), Status.valueOf(rs.getString("reim_request_status").toUpperCase(Locale.ROOT).replaceAll(" ","_").toUpperCase(Locale.ROOT))
                        ,Timeing.valueOf(rs.getString("reim_request_timing").toUpperCase(Locale.ROOT).replaceAll(" ","_")));
                    userReimRequest.add(r);
            }
            System.out.println("Reimbursments Retrieved");
            return userReimRequest;


        }catch (SQLException s){
            s.printStackTrace();
        }
        return null;

    }

    public List<ReimbursementRequest> reimbursementRequestGetAllRequest() {
        String sql = "select * from reimbursement_request";
        List<ReimbursementRequest> userReimRequest=new ArrayList<>();
        try(Connection conn=cu.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ReimbursementRequest r=new ReimbursementRequest(EventType.valueOf(rs.getString("event_type").toUpperCase(Locale.ROOT).replaceAll(" ","_")),
                        rs.getString("gradeing_format"),
                        rs.getString("loc_work_rel_doc"),
                        userDAO.getByUserId(rs.getInt("user_id")).get(),
                        rs.getDate("event_dt").toString(),
                        rs.getDouble("reim_cost"),
                        Status.valueOf(rs.getString("reim_request_status").toUpperCase(Locale.ROOT).replaceAll(" ","_").toUpperCase(Locale.ROOT)),
                        Timeing.valueOf(rs.getString("reim_request_timing").toUpperCase(Locale.ROOT).replaceAll(" ","_")));
                userReimRequest.add(r);
            }

            return userReimRequest;


        }catch (SQLException s){
            s.printStackTrace();
        }
        return null;

    }
    public void updateReimRequest(Timeing value,int userId){
        try(Connection conn=cu.getConnection()) {

            String sql = "update reimbursement_request set reim_request_timing=? where user_id=? ";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, value.toString());
            ps.setInt(2, userId);

        }catch (SQLException s){
                s.printStackTrace();
            }




    }
}
