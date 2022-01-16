package com.revature.repositories;

import com.revature.MockDb.Database;
import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.exceptions.UserNameException;
import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.models.User;
import com.revature.util.FrontEndClass;

import java.util.Optional;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) {
        for (User u : Database.users) {
            if (u.getUsername().equals(username)) {
                return Optional.of(u);
            }
        }
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
        for (User u : Database.users) {
            if (u.getUsername().equals(userToBeRegistered.getUsername())) {
                UsernameNotUniqueException excep = new UsernameNotUniqueException();
                ;
                throw excep;
            }

        }
        Database.users.add(userToBeRegistered);
    return userToBeRegistered;}
}
             /*try {
                 throw regexcep;

             //}finally {
                 FrontEndClass throwBack  = new FrontEndClass();
                 System.out.println("Username already taken please press 'Enter' and follow the menu");
                 throwBack.createUserScreen();
             }

         }
       }

        Database.users.add(userToBeRegistered);

        return userToBeRegistered;
    }
} */
