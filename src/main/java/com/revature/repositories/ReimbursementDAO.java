package com.revature.repositories;

import com.revature.MockDb.Database;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementRequest;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.services.ReimbursementRequestService;

import java.util.*;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    public Optional<Reimbursement> getById(int id) {
        return Optional.empty();
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
        return Collections.emptyList();
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
    	return null;
    }


    public void reimbursementRequestCreate(User u, ArrayList<ReimbursementRequest> arrayList){

        Database.reimbursements.put(u,arrayList);
    }
    public  boolean reimbursementGetExistStat(User u){
        if (Database.reimbursements.isEmpty()){
            return Database.reimbursements.isEmpty();
        }
        else{
            return Database.reimbursements.get(u).isEmpty();
        }
    }
    public void addReimbusementRequest(User u, ReimbursementRequest r){
        Database.reimbursements.get(u).add(r);
    }

}

