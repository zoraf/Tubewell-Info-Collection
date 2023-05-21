package com.example.tubewellinfocollection.POJO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserInformation implements Serializable {

    @SerializedName("userName")
    private String userName;

    @SerializedName("organizationName")
    private String organizationName;

    @SerializedName("mobileNumber")
    private String mobileNumber;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public UserInformation() {
    }

    public UserInformation(String userName, String organizationName, String mobileNumber, String email, String password) {
        this.userName = userName;
        this.organizationName = organizationName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
