package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ControlerInterface {
    AuthService authService= new AuthService();
    Reimbursement reimbursement=new Reimbursement();
    UserService userService=new UserService();
}
