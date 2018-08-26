package com.pacosanchez.emergencysystem;

import java.util.ArrayList;

public class User {


    String sEmail;
    String sName;
    String sId;
    String phoneNumber;
    ArrayList<familyMember> familyMembers = new ArrayList<>();

    public  User()
    {}

    public User(String sEmail, String sName, String sId) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sId = sId;
    }

    public String getsEmail() {
        return sEmail;
    }

    public String getsName() {
        return sName;
    }

    public String getsId() {
        return sId;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

}