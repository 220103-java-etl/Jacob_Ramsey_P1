package com.revature.util;

import com.revature.Driver;
import com.revature.MockDb.Database;
import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.models.*;
import com.revature.repositories.ReimbursementDAO;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementRequestService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;


import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class FrontEndClass {
    private static int I=0;
    UserService userService=new UserService();
    AuthService authService=new AuthService();
    ReimbursementRequestService reimbursementRequestService=new ReimbursementRequestService();
    ReimbursementService reimbursementService=new ReimbursementService();
    Scanner scanner=new Scanner(System.in);


    public FrontEndClass(int b){
        this.homeScreen();

    }
    public FrontEndClass(){
        System.out.println(Database.users);
    }


    public void createUserScreen() {



        System.out.println("If you would like to go back to the HomeScreen enter 2 else enter 1 to continue to create a user");
        scanner.nextLine();
        int i = scanner.nextInt();
        scanner.nextLine();
        switch (i) {
            case 1:
                System.out.println("Type what you want your username to be");
                String userName = scanner.nextLine();
                System.out.println("Type what you want your password to be");
                String passWord = scanner.nextLine();
                System.out.println("What is your role if you are an employee type 'Employee' or else enter the password for a Finance Manager");
                StringBuilder role= new StringBuilder(scanner.nextLine().strip().toUpperCase(Locale.ROOT));




                if(role.toString().equals("EMPLOYEE")) {
                    User user = new User(userName, passWord, Role.EMPLOYEE, 1000);
                    authService.register(user);
                    System.out.println("You've created an account!");


                }else if(role.toString().equals("SECRET_PASS")) {
                    User user = new User(userName, passWord, Role.FINANCE_MANAGER, 0);
                    authService.register(user);
                    System.out.println("You've created an account!");
                }else{

                    throw new RegistrationUnsuccessfulException("Whoops you must have entered your role incorrectly");
                }


            case 2:
                homeScreen();


        }

    }

    public void createUserScreenFail() {
        System.out.println("That user already exists try again with a different name");
        createUserScreen();

    }

    public void homeScreen() {
        System.out.println("Please select 1 of the following options");
        System.out.println("1.Login");
        System.out.println("2.Create User");
        int n = scanner.nextInt();

        switch (n) {
            case 1:
                loginScreen();
                break;
            case 2:
                createUserScreen();


        }

    }
    public void loginScreen(){
        scanner.nextLine();
        System.out.println("Please enter your Username");
        String userName=scanner.nextLine();
        System.out.println("Please enter your Password");
        String passWord=scanner.nextLine();
        User currentUser=authService.login(userName,passWord);
        if(currentUser.getRole()==Role.FINANCE_MANAGER) {
            postLoginSecreenManager(currentUser);
        }else{
            postLoginScreenEmployee(currentUser);
        }


    }
    public void postLoginScreenEmployee(User u){
        System.out.println("You are now logged in");
        System.out.println("1. View your reimbursement requests");
        System.out.println("2. Check your available reimbursment left");
        System.out.println("3. Create a reimbursment request");
        System.out.println("4. Logout");
        int numSelected= scanner.nextInt();

        switch(numSelected){
            case 1:
                System.out.println(reimbursementRequestService.getReimbursementReq(u));
                postLoginScreenEmployee(u);
                break;
            case 2:
                System.out.println(u.getAvailableReimbursement());
                postLoginScreenEmployee(u);
                break;
            case 3:
                reimbursementRequest(u);
                break;
            case 4:
                homeScreen();

        }
    }

    public void postLoginSecreenManager(User u){

        System.out.println("You are now logged in");
        System.out.println("1. Validate or Invalidate a request form");
        System.out.println("2. Approve or Deny a request for reimbursement");
        System.out.println("3. List current request forms to be validated");
        System.out.println("4. List current request forms that need to be approved");

        int numSelected=scanner.nextInt();

        switch(numSelected){
            case 1:

                System.out.println("Type the Id for the request form you would like to validate or invalidate");
                int requestFormId= scanner.nextInt();
                System.out.println("Now type I to Invalidate or V to Validate");
                scanner.nextLine();
                String choice= scanner.nextLine().toUpperCase(Locale.ROOT);
                if(choice.equals("V")){
                    reimbursementRequestService.updateReimRequestValidatyService(Status.APPROVED, requestFormId);
                    reimbursementService.addReimbusementService(requestFormId,u);
                }
                else if(choice.equals("I")){
                    reimbursementRequestService.updateReimRequestValidatyService(Status.DENIED, requestFormId);
                }
                else{
                    System.out.println("something went wrong");
                }

        }

    }

    public void reimbursementRequest(User u){
        scanner.nextLine();
        System.out.println("Type the date of the event in 'YYYY-MM-DD' format");
        String dateOfevent= scanner.nextLine();

        System.out.println("Type the gradeing foremat");
        String gradeingFormat=scanner.nextLine();
        System.out.println("Type the event Type, must be either University Course, Seminar,Certification Preporation Class," +
                "Technical Training, Other, Certification");
        String eventType=scanner.nextLine();
        StringBuilder sbEveTyp=new StringBuilder(eventType.strip().toUpperCase(Locale.ROOT));
        System.out.println("Type the cost of the course in the form a double form");
        BigDecimal eventCost= BigDecimal.valueOf( scanner.nextDouble());

        switch(sbEveTyp.toString()){
            case "UNIVERSITY COURSE":
                ReimbursementRequest r=new ReimbursementRequest(EventType.UNIVERSITY_COURSE,gradeingFormat,"proof",u,dateOfevent,eventCost,
                        Status.PENDING,Timeing.NORMAL);
                reimbursementRequestService.reimbursementCreate(u,r);

                postLoginScreenEmployee(u);
                break;
            case "SEMINAR":
                ReimbursementRequest g=new ReimbursementRequest(EventType.SEMINAR,gradeingFormat,"proof",u,dateOfevent,eventCost,
                        Status.PENDING,Timeing.NORMAL);
                reimbursementRequestService.reimbursementCreate(u,g);

                postLoginScreenEmployee(u);
                break;
            case"CERTIFICATION PREPORATION CLASS":
                ReimbursementRequest d=new ReimbursementRequest(EventType.CERTIFICATION_PREP_CLASS,gradeingFormat,"proof",u,dateOfevent,eventCost,
                        Status.PENDING,Timeing.NORMAL);
                reimbursementRequestService.reimbursementCreate(u,d);

                postLoginScreenEmployee(u);
                break;
            case"CERTIFICATION":
                ReimbursementRequest n=new ReimbursementRequest(EventType.CERTIFICATION,gradeingFormat,"proof",u,dateOfevent,eventCost,
                        Status.PENDING,Timeing.NORMAL);
                reimbursementRequestService.reimbursementCreate(u,n);


                break;
            case"TECHNICAL TRAINING":
                ReimbursementRequest p=new ReimbursementRequest(EventType.TECHNICAL_TRAINING,gradeingFormat,"proof",u,dateOfevent,eventCost,
                        Status.PENDING,Timeing.NORMAL);
                reimbursementRequestService.reimbursementCreate(u,p);

                postLoginScreenEmployee(u);
                break;
            case"OTHER":
                ReimbursementRequest q=new ReimbursementRequest(EventType.OTHER,gradeingFormat,"proof",u,dateOfevent,eventCost,
                        Status.PENDING,Timeing.NORMAL);
                reimbursementRequestService.reimbursementCreate(u,q);

                postLoginScreenEmployee(u);
        }

    }
}
