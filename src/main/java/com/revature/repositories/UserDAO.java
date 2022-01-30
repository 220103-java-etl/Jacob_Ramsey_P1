package com.revature.repositories;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserDAO {

    ConnectionFactory cu = ConnectionFactory.getInstance();
    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) {
        String sql = "select * from users where user_name=?" ;
        try(Connection conn=cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User(rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("pass_word"),
                        Role.valueOf(rs.getString("roles").toUpperCase(Locale.ROOT).replaceAll(" ","_")),
                        rs.getBigDecimal("available_reimbursement"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"));
                return Optional.of(u);
            }
        }catch(SQLException e){
            e.printStackTrace();}
          return Optional.empty();

    }
    public Optional<User> getByUserId(int userID) {
        String sql = "select * from users where id=?" ;
        try(Connection conn=cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,userID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User(rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("pass_word"),
                        Role.valueOf(rs.getString("roles").toUpperCase(Locale.ROOT).replaceAll(" ","_")),
                        rs.getBigDecimal("available_reimbursement"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"));
                return Optional.of(u);
            }
        }catch(SQLException e){
            e.printStackTrace();}
        return Optional.empty();

    }
    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     * <p>
     * Note: The userToBeRegistered will have an id=0, and username and password will not be null.
     * Additional fields may be null.
     */
    public User create(User userToBeRegistered) {
        String sql = "insert into users values (default, ?, ?,?,?,?,?,?) returning *" ;

        try(Connection conn=cu.getConnection()){
            PreparedStatement  ps = conn.prepareStatement(sql);
            ps.setString(1,userToBeRegistered.getUsername());
            ps.setString(2,userToBeRegistered.getPassword());
            ps.setString(3,userToBeRegistered.getRole().toString());
            ps.setDouble(4,userToBeRegistered.getAvailableReimbursement().doubleValue());
            ps.setString(5,userToBeRegistered.getfName());
            ps.setString(6,userToBeRegistered.getlName());
            ps.setString(7,userToBeRegistered.getEmail());

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                User u=new User(rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("pass_word"),
                      Role.valueOf( rs.getString("roles").toUpperCase(Locale.ROOT).replaceAll(" ","_")),
                        rs.getBigDecimal("available_reimbursement"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"));
                return u;
            }

        }catch (SQLException s){
            s.printStackTrace();
        }
        return null;

    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        // this is our sequel statement -> we want it to return all records from the table
        String sql = "select * from users";
        // try-with-resources -> used to automatically close resources after the try/catch/finally
        try (Connection conn = cu.getConnection()) {

            // Prepare the statement
            PreparedStatement ps = conn.prepareStatement(sql);

            // Execute the statement and save the ResultSet into an object
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               User a = new User(

                       rs.getString("user_name"),
                       rs.getString("pass_word"),
                       Role.valueOf(rs.getString("roles")),
                       rs.getBigDecimal("available_reimbursement"),
                       rs.getString("first_name"),
                       rs.getString("last_name"),
                       rs.getString("email")
                );
                users.add(a);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateAvailableReimbursement(BigDecimal requestAmount,User u){
        try (Connection conn = cu.getConnection()) {

        String sql = "update users set available_reimbursement=? where id=? ";

        PreparedStatement ps = conn.prepareStatement(sql);
          BigDecimal subtract= u.getAvailableReimbursement().subtract(requestAmount);
        ps.setBigDecimal(1,subtract);
        ps.setInt(2, u.getId());
        ps.executeQuery();
    } catch (SQLException s) {
        s.printStackTrace();
    }

    }
}
