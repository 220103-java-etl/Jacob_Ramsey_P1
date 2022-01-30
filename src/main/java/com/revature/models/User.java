package com.revature.models;


import java.math.BigDecimal;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>
 *     <li>Phone Number</li>
 *     <li>Address</li>
 * </ul>
 *
 */
public class User extends AbstractUser {

    public User() {
        super();

    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractUser} class.
     * If other fields are needed, please create additional constructors.
     */
    public User( String username, String password, Role role,String fName,String lName, String email) {
        super( username, password, role,fName,lName,email);

    }
    public User( String username, String password, Role role,BigDecimal availableReimbursement,String fName,String lName, String email) {
        super( username, password, role,fName,lName,email);
        this.setAvailableReimbursement(availableReimbursement);

    }
    public User(int id, String username, String password, Role role, BigDecimal availableReimbursement, String fName, String lName, String email) {
        super( id,username, password, role,fName,lName,email);
        this.setAvailableReimbursement(availableReimbursement);
    }
}
