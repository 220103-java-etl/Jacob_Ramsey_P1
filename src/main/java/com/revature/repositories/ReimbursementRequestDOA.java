package com.revature.repositories;

import com.revature.models.*;
import com.revature.util.ConnectionFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ReimbursementRequestDOA {
    UserDAO userDAO = new UserDAO();
    ConnectionFactory cu = ConnectionFactory.getInstance();

    public void reimbursementRequestCreate(ReimbursementRequest reimbursementRequest, User u) {
        String sql = "insert into reimbursement_request values (default, ?, ?,?,?,?,?,?,?,?,?) returning *";

        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, reimbursementRequest.getEventType().toString());
            ps.setString(2, reimbursementRequest.getGradeingFormat());
            ps.setString(3, reimbursementRequest.getStandIndocProof());
            ps.setInt(4, u.getId());
            ps.setBigDecimal(5, reimbursementRequest.getReimbursmentAmount());
            ps.setDate(6, reimbursementRequest.getCurrentDate());
            ps.setDate(7, reimbursementRequest.getDateOfEvent());
            ps.setString(8, reimbursementRequest.getStatus().toString());
            ps.setString(9, reimbursementRequest.getTimeing().toString());
            ps.setString(10,reimbursementRequest.getLocation());
            ResultSet rs = ps.executeQuery();
            userDAO.updateAvailableReimbursement(reimbursementRequest.getReimbursmentAmount(),u);



        } catch (SQLException s) {
            s.printStackTrace();
        }


    }

    public List<ReimbursementRequest> reimbursementRequestGetByUserName(User u) {
        String sql = "select * from reimbursement_request where user_id=?";
        List<ReimbursementRequest> userReimRequest = new ArrayList<>();
        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, u.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReimbursementRequest r = new ReimbursementRequest(
                        rs.getInt("id"),
                        EventType.valueOf(rs.getString("event_type").toUpperCase(Locale.ROOT).replaceAll(" ", "_")),
                        rs.getString("gradeing_format"), rs.getString("related_doc"), u, rs.getDate("event_dt").toString(),
                        rs.getBigDecimal("reim_cost"), Status.valueOf(rs.getString("reim_request_status").toUpperCase(Locale.ROOT).replaceAll(" ", "_").toUpperCase(Locale.ROOT))
                        , Timeing.valueOf(rs.getString("reim_request_timing").toUpperCase(Locale.ROOT).replaceAll(" ", "_")),rs.getString("location"));
                userReimRequest.add(r);
            }
            System.out.println("Reimbursments Retrieved");
            return userReimRequest;


        } catch (SQLException s) {
            s.printStackTrace();
        }
        return null;

    }

    public Optional<ReimbursementRequest> reimbursementRequestGetById(int Id) {
        String sql = "select * from reimbursement_request where id=?";

        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReimbursementRequest r = new ReimbursementRequest(Id, EventType.valueOf(rs.getString("event_type").toUpperCase(Locale.ROOT).replaceAll(" ", "_")),
                        rs.getString("gradeing_format"), rs.getString("related_doc"), userDAO.getByUserId(rs.getInt("user_id")).get(), rs.getDate("event_dt").toString(),
                        rs.getBigDecimal("reim_cost"), Status.valueOf(rs.getString("reim_request_status").toUpperCase(Locale.ROOT).replaceAll(" ", "_").toUpperCase(Locale.ROOT))
                        , Timeing.valueOf(rs.getString("reim_request_timing").toUpperCase(Locale.ROOT).replaceAll(" ", "_")),rs.getString("location"));
                return Optional.of(r);
            }



        } catch (SQLException s) {
            s.printStackTrace();
        }
        return null;

    }

    public List<ReimbursementRequest> reimbursementRequestGetAllRequest() {
        String sql = "select * from reimbursement_request ";
        List<ReimbursementRequest> userReimRequest = new ArrayList<>();
        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReimbursementRequest r =
                        new ReimbursementRequest(
                                rs.getInt("id"),
                                EventType.valueOf(rs.getString("event_type").toUpperCase(Locale.ROOT).replaceAll(" ", "_")),
                                rs.getString("gradeing_format"),
                                rs.getString("related_doc"),
                                userDAO.getByUserId(rs.getInt("user_id")).get(),
                                rs.getDate("event_dt").toString(),
                                rs.getBigDecimal("reim_cost"),
                                Status.valueOf(rs.getString("reim_request_status").toUpperCase(Locale.ROOT).replaceAll(" ", "_").toUpperCase(Locale.ROOT)),
                                Timeing.valueOf(rs.getString("reim_request_timing").toUpperCase(Locale.ROOT).replaceAll(" ", "_")),rs.getString("location"));
                userReimRequest.add(r);
            }

            return userReimRequest;


        } catch (SQLException s) {
            s.printStackTrace();
        }
        return null;

    }

    public void updateReimRequestTimeing(Timeing value, int Id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursement_request set reim_request_timing=? where id=? ";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, value.toString());
            ps.setInt(2, Id);
            ps.executeQuery();
        } catch (SQLException s) {
            s.printStackTrace();
        }


    }
    public BigDecimal updateReimRequestAmount(BigDecimal amount, int Id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursement_request set reim_cost=? where id=? ";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setBigDecimal(1, amount);
            ps.setInt(2, Id);
            ps.executeQuery();
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return amount;

    }

    public String updateReimRequestInfo(String info, int Formid) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursement_request set related_doc=? where id=? ";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, info);
            ps.setInt(2, Formid);
            ps.executeQuery();
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return info;

    }

    public void updateReimRequestValidaty(Status status, int userFormId) throws SQLException {

        Connection conn = cu.getConnection();

            String sql = "update reimbursement_request set reim_request_status=? where id=? ";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, status.toString());
            ps.setInt(2, userFormId);
            ps.executeQuery();


    }

    public void deleteReimbursementRequest(int userFormId) {

        try (Connection conn = cu.getConnection()) {
            deleteReimbursementRequestValidated(userFormId);
            String sql = "delete from reimbursement_request where id=? ";

            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setInt(1, userFormId);
            ps.executeQuery();
            System.out.println("You've deleted the form ");
        } catch (SQLException s) {
            s.printStackTrace();
        }


    }
    public void deleteReimbursementRequestValidated(int userFormId) {

        try (Connection conn = cu.getConnection()) {

            String sql = "delete from reimbursement_req_accepted where reim_form=? ";

            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setInt(1, userFormId);
            ps.executeQuery();
            System.out.println("You've deleted the form ");
        } catch (SQLException s) {

            s.printStackTrace();
        }


    }

    public String getAccessValueForRequestById(int formId){
        String sql = "select * from reimbursement_request where id=?";

        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, formId);
            ResultSet rs = ps.executeQuery();
            String result=null;
            while (rs.next()) {
                 result = rs.getString("alter_constraints");
            }
            return result;


        } catch (SQLException s) {
            s.printStackTrace();
        }
        return null;
    }
    public void updateFormAccess(String s,int id) {
        try (Connection conn = cu.getConnection()) {

            String sql = "update reimbursement_request set alter_constraints=? where id=? ";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, s);
            ps.setInt(2, id);
            ps.executeQuery();
        } catch (SQLException m) {
            m.printStackTrace();
        }


    }


}


