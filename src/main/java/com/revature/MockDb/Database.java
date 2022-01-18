package com.revature.MockDb;

import com.revature.models.EventType;
import com.revature.models.ReimbursementRequest;
import com.revature.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class Database {
    public static int Index=0;
    public static List<User> users= new ArrayList<>();
    public static List<ReimbursementRequest> reimbursementRequests=new ArrayList<>();
    public static Map<User, ArrayList<ReimbursementRequest>> reimbursements= new HashMap<>();

}
