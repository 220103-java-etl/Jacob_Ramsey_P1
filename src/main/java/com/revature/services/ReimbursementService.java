package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li>
 *     <li>Update Reimbursement</li>
 *     <li>Get Reimbursements by ID</li>
 *     <li>Get Reimbursements by Author</li>
 *     <li>Get Reimbursements by Resolver</li>
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {

    ReimbursementDAO reimbursementDAO = new ReimbursementDAO();

    /**
     * <ul>
     *     <li>Should ensure that the user is logged in as a Finance Manager</li>
     *     <li>Must throw exception if user is not logged in as a Finance Manager</li>
     *     <li>Should ensure that the reimbursement request exists</li>
     *     <li>Must throw exception if the reimbursement request is not found</li>
     *     <li>Should persist the updated reimbursement status with resolver information</li>
     *     <li>Must throw exception if persistence is unsuccessful</li>
     * </ul>
     * <p>
     * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID and amount, and a non-null Author.
     * The Resolver should be null. Additional fields may be null.
     * After processing, the reimbursement will have its status changed to either APPROVED or DENIED.
     */
    public Reimbursement process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver) {
        return null;
    }

    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<Reimbursement> getReimbursementsByStatus(Status status) {
        return Collections.emptyList();
    }

    public List<Reimbursement> getByReimbursementResolver(int resolverId) {
        return reimbursementDAO.getByResolverId(resolverId);
    }

    public void addReimbusementService(int reimFormId, User u, String s, String s1) {

        Reimbursement reimbursement = new Reimbursement(reimFormId, Status.PENDING, u,s);
        reimbursementDAO.addReimbusementDOA(reimbursement);
    }

    public void updateStatus(Status s, int Id) throws SQLException {
        reimbursementDAO.updateStatus(s, Id);
    }

    public void updateGrade(String s, int Id) {
        reimbursementDAO.updateFinalGrade(s, Id);
    }

    public void insertFinalGrade(String s,int i) {
        reimbursementDAO.insertFinalGrade(s,i);
    }
}
