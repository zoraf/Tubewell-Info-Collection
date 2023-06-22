package com.example.tubewellinfocollection.Service;

import com.example.tubewellinfocollection.POJO.LoginInformation;
import com.example.tubewellinfocollection.POJO.LoginResponse;
import com.example.tubewellinfocollection.POJO.TubewellInformation;
import com.example.tubewellinfocollection.POJO.TubewellInformationSubmissionResponse;
import com.example.tubewellinfocollection.POJO.UserRegistrationResponse;
import com.example.tubewellinfocollection.POJO.UserRegistrationInformation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/login")
    Call<LoginResponse> login(@Body LoginInformation loginInformation);

    @POST("/registration")
    Call<UserRegistrationResponse> registration(@Body UserRegistrationInformation userRegistrationInformation);


    @POST("/tubewell_information")
    Call<TubewellInformationSubmissionResponse> submitTubewellInformation(@Body TubewellInformation tubewellInformation);

}
