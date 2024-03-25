package com.pointsmallsystem.www.po;

public class Merchant extends User{
    private String address;

    public Merchant(){}
    public Merchant(String username, String password, String gender, String phoneNumber, String email, String address) {
       super(username,password,gender,phoneNumber,email);
       this.address=address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
