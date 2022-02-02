package com.revature.services;

import com.revature.exceptions.PassWordException;
import com.revature.exceptions.UserNameException;
import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

import java.util.Optional;

/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Retrieve Currently Logged-in User</li>
 *     <li>Change Password</li>
 *     <li>Logout</li>
 * </ul>
 */
public class AuthService {
    private UserDAO userDAO =new UserDAO();

    /**
     * <ul>
     *     <li>Needs to check for existing users with username/email provided.</li>
     *     <li>Must throw exception if user does not exist.</li>
     *     <li>Must compare password provided and stored password for that user.</li>
     *     <li>Should throw exception if the passwords do not match.</li>
     *     <li>Must return user object if the user logs in successfully.</li>
     * </ul>
     */
    public User login(String username, String password) {
        Optional<User> u = userDAO.getByUsername(username);
        if(u.isEmpty()){
            UserNameException userNameException=new UserNameException("Username does not exist");
            throw userNameException;

        }
        if (u.get().getUsername().equals(username)) {
            if (u.get().getPassword().equals(password)) {

                return u.get();
            } else {
                PassWordException passWordException = new PassWordException("Your password was incorrect");
                throw passWordException;
            }
        }
        return null;
    }

    /**
     * <ul>
     *     <li>Should ensure that the username/email provided is unique.</li>
     *     <li>Must throw exception if the username/email is not unique.</li>
     *     <li>Should persist the user object upon successful registration.</li>
     *     <li>Must throw exception if registration is unsuccessful.</li>
     *     <li>Must return user object if the user registers successfully.</li>
     *     <li>Must throw exception if provided user has a non-zero ID</li>
     * </ul>
     *
     * Note: userToBeRegistered will have an id=0, additional fields may be null.
     * After registration, the id will be a positive integer.
     */
    public User register(User userToBeRegistered) {
        if(userDAO.getByUsername(userToBeRegistered.getUsername()).isEmpty()){
        userDAO.create(userToBeRegistered);
        return userToBeRegistered;}
        else{
            UsernameNotUniqueException u=new UsernameNotUniqueException("User name is not unique");
            throw u;
        }
    }

    /**
     * This is an example method signature for retrieving the currently logged-in user.
     * It leverages the Optional type which is a useful interface to handle the
     * possibility of a user being unavailable.
     */
    public Optional<User> exampleRetrieveCurrentUser() {
        return Optional.empty();
    }
}
