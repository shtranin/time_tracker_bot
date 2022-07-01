package com.example.userStatementManager;

import java.util.HashMap;
import java.util.Map;

public class UserStatementManager {
    private static Map<Long, Statements> userStatements = new HashMap<>();


    public static Statements getStatementById(Long userId){
        return userStatements.get(userId);
    }
    public static void setStatement(Long userId, Statements statement){
        userStatements.put(userId,statement);
    }

     public static boolean userHasStatement(Long userId){
       return userStatements.containsKey(userId);
    }
    public static void makeUserFree(Long userId){
        userStatements.remove(userId);
    }
}
