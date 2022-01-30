package com.revature.repositories;

import com.revature.models.EventType;
import com.revature.models.ReimbursementRequest;
import com.revature.models.Status;
import com.revature.models.Timeing;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class MessageBoardDAO {UserDAO userDAO = new UserDAO();
    ConnectionFactory cu = ConnectionFactory.getInstance();

    public String sendMessage(int senderId,int recieverId, String message){
        String sql = "insert into message_board values (?,?,?)" ;

        try(Connection conn=cu.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, senderId);
            ps.setInt(2, recieverId);
            ps.setString(3, message);
            ps.executeQuery();

           String messageSent="Message Sent Successfully";

            return messageSent;

        }catch (SQLException s){
            s.printStackTrace();
        }
        return null;
    }

    public List<String> getAllMessagesByRecieverId(int recieverId) {
        String sql = "select * from message_board where reciever=?";
        List<String> userMessages = new ArrayList<>();
        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,recieverId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String s = rs.getString("message");

                userMessages.add(s);
            }

            return userMessages;


        } catch (SQLException s) {
            s.printStackTrace();
        }
        return null;

    }
}
