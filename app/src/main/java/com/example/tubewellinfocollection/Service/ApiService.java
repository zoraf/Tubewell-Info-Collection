package com.example.tubewellinfocollection.Service;

import com.example.tubewellinfocollection.POJO.LoginInformation;
import com.example.tubewellinfocollection.POJO.LoginResponse;
import com.example.tubewellinfocollection.POJO.RegistrationInformation;
import com.example.tubewellinfocollection.POJO.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("/login")
    Call<LoginResponse> login(@Body LoginInformation loginInformation);

    @POST("/registration")
    Call<RegistrationResponse> registration(@Body RegistrationInformation registrationInformation);




}
