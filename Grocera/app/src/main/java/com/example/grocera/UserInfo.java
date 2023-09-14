package com.example.grocera;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class UserInfo {
    private String email;
    private String phone;
    private  String name;
    private  String address;
    public UserInfo(){}
    public UserInfo(String email, String phone, String name, String address){
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

}
