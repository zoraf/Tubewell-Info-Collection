package com.example.tubewellinfocollection.POJO;

import com.google.gson.annotations.SerializedName;

public class UserRegistrationResponse {
    @SerializedName("response")
    private String response;

    @SerializedName("responseCode")
    private int responseCode;

    public UserRegistrationResponse() {
    }

    public UserRegistrationResponse(String response, int responseCode) {
        this.response = response;
        this.responseCode = responseCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}