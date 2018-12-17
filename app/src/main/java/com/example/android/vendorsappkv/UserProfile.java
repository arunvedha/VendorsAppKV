package com.example.android.vendorsappkv;

public class UserProfile {
    public String userName;
    public String aadhar;
    public String address;

    public UserProfile(){
    }

    public UserProfile(String userName, String aadhar, String address){
        this.userName = userName;
        this.aadhar = aadhar;
        this.address = address;
    }

    public String getAadhar() {
        return aadhar;
    }

    public String getAddress() {
        return address;
    }


    public String getUserName() {
        return userName;
    }

}
