package com.revature.repositories;


import com.revature.models.*;

import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ReimbursementDAO {
    UserDAO userDAO=new UserDAO();
    ConnectionFactory cu=ConnectionFactory.getInstance();
    ReimbursementRequestDOA reimbursementRequestDOA=new ReimbursementRequestDOA();
    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    public List<Reimbursement> getByResolverId(int id) {
        String sql = "select * from reimbursement_req_accepted where form_resolver=?";

        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            List<Reimbursement> reimbursements=new ArrayList<>();
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement r = new Reimbursement(
                        rs.getInt("reim_form"),
                        Status.valueOf(rs.getString("reimbursement_status").toUpperCase(Locale.ROOT)),
                        userDAO.getByUserId(rs.getInt("form_resolver")).get(),rs.getString("inserted_final_grade"));
                reimbursements.add(r);
            }
            return reimbursements;



        } catch (SQLException s) {
            s.printStackTrace();
        }
        return null;

    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
        String sql = "select * from reimbursement_req_accepted where reimbursement_status=?";
        List<Reimbursement> userReim = new ArrayList<>();
        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,status.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reimbursement r =
                        new Reimbursement(
                                rs.getInt("reim_form"),
                                Status.valueOf(rs.getString("reimbursement_status").toUpperCase(Locale.ROOT)),
                               userDAO.getByUserId(rs.getInt("form_resolver")).get(),rs.getString("inserted_final_grade"));
                userReim.add(r);
            }

            return userReim;
    } catch (SQLException s) {
        s.printStackTrace();
    }
        return null;

}

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public void updateStatus(Status status, int userFormId) throws SQLException {
         Connection conn = cu.getConnection();

            String sql = "update reimbursement_req_accepted set reimbursement_status=? where reim_form=? ";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, status.toString());
            ps.setInt(2, userFormId);
            ps.executeQuery();




    }

    public void updateFinalGrade(String grade, int userFormId) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursement_req_accepted set final_grade_completion_of_pres=? where reim_form=? ";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, grade);
            ps.setInt(2, userFormId);
            ps.executeQuery();


        } catch (SQLException s) {
            s.printStackTrace();
        }

    }
    public void insertFinalGrade(String grade, int userFormId) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursement_req_accepted set inserted_final_grade=? where reim_form=? ";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, grade);
            ps.setInt(2, userFormId);
            ps.executeQuery();


        } catch (SQLException s) {
            s.printStackTrace();
        }

    }



    public void addReimbusementDOA( Reimbursement r){

        String sql = "insert into reimbursement_req_accepted values (?,?,?,default)" ;

        try(Connection conn=cu.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, r.getId());
            ps.setInt(2, r.getResolver().getId());
            ps.setString(3, Status.PENDING.toString());
            ps.executeQuery();

            System.out.println("Reimbursment Created");



        }catch (SQLException s){
            s.printStackTrace();
        }


    }
    public void deleteReimbursement(int userFormId){
        try (Connection conn = cu.getConnection()) {

        String sql = "delete from reimbursement_req_accepted where reim_form=? ";

        PreparedStatement ps = conn.prepareStatement(sql);


        ps.setInt(1, userFormId);
        ps.executeQuery();
        reimbursementRequestDOA.deleteReimbursementRequest(userFormId);
        System.out.println("You've deleted the form ");

    } catch (SQLException s) {
        s.printStackTrace();
    }


}


    }



