package com.sai.mrsai.models;

public class UserDetails {
    String userName,userMail,userPhone,uid;

    public UserDetails() {
    }

    public UserDetails(String uid,String userName, String userMail, String userPhone) {
        this.userName = userName;
        this.userMail = userMail;
        this.userPhone = userPhone;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
