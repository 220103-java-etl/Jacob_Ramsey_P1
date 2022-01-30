package com.revature.services;

import com.revature.repositories.MessageBoardDAO;

import java.util.List;

public class MessageService {
    MessageBoardDAO messageBoardDAO=new MessageBoardDAO();

    public String sendMessageToUser(int sender,int reciever,String message){
        return messageBoardDAO.sendMessage(sender,reciever,message);
    }

    public List<String> getMessages(int userId){
        return messageBoardDAO.getAllMessagesByRecieverId(userId);
    }

}
