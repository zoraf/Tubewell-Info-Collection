package com.example.tubewellinfocollection.POJO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginInformation implements Serializable {

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("password")
    private String password;

    public LoginInformation() {
    }

    public LoginInformation(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
