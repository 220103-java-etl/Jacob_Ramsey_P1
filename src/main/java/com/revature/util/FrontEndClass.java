package com.revature.util;

import com.revature.Driver;
import com.revature.MockDb.Database;
import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;


import java.util.Locale;
import java.util.Scanner;

public class FrontEndClass {
    UserService userService=new UserService();
    AuthService authService=new AuthService();
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
                System.out.println(Database.users);



                if(role.toString().equals("EMPLOYEE")){
                User user = new User( 1,userName, passWord, Role.EMPLOYEE);
                    authService.register(user);
                    System.out.println("You've created an account!");}

                else if(role.toString().equals("SECRET_PASS")){
                    User user = new User( 1,userName, passWord, Role.FINANCE_MANAGER);
                    authService.register(user);
                    System.out.println("You've created an account!");}
                else{

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
        authService.login(userName,passWord);
        postLoginScreenEmployee();

    }
    public void postLoginScreenEmployee(){

        System.out.println("1. View your reimbursement requests");
        System.out.println("2. Check your available reimbursment left");
        System.out.println("3. Create a reambursment request");
        scanner.nextInt();
    }
}
