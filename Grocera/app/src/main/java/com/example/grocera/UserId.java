package com.example.grocera;

public class UserId {
    private static String userId;

    public static void setUserId(String user){
        userId = user;
    }
    public static String getUserId(){
        return userId;
    }
}
