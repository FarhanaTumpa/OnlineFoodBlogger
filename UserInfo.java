package com.example.user.onlinefoodbloggers;

public class UserInfo {

    private String name;
    private String email;
    private String Pass;
    private String Phone;

    public UserInfo(String name, String email, String pass, String phone) {
        this.name = name;
        this.email = email;
        Pass = pass;
        Phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
