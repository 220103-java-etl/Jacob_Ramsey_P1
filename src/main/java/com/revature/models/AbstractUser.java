package com.revature.models;



import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * This AbstractUser class defines a minimum functionality for
 * interacting with users in the ERS application.
 *
 * All users in this application must at least have:
 * <ul>
 *     <li>ID</li>
 *     <li>Username</li>
 *     <li>Password</li>
 *     <li>Role</li>
 * </ul>
 *
 * Additional fields can be added to the concrete {@link com.revature.models.User} class.
 *
 * @author Center of Excellence
 */

public class AbstractUser  {
    private BigDecimal availableReimbursement;
    private int id;
    private String username;
    private String password;
    private String fName;
    private String lName;
    private String email;
    private Role role;

    public AbstractUser() {
        super();
    }

    public AbstractUser( String username, String password, Role role,String fName,String lName, String email) {
        super();

        this.username = username;
        this.password = password;
        this.role = role;
        this.fName=fName;
        this.lName=lName;
        this.email=email;
    }



    public AbstractUser(int id, String username, String password, Role role,String fName,String lName, String emai) {
        super();
        this.id=id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.fName=fName;
        this.lName=lName;
        this.email=email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getAvailableReimbursement() {

        return this.availableReimbursement;
    }

    public void setAvailableReimbursement(BigDecimal availableReimbursement) {
        this.availableReimbursement = availableReimbursement;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUser that = (AbstractUser) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(password, that.password) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role);
    }

    @Override
    public String toString() {
        return "AbstractUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
