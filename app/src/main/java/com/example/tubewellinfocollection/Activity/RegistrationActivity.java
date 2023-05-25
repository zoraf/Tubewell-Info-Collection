package com.example.tubewellinfocollection.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tubewellinfocollection.POJO.UserRegistrationResponse;
import com.example.tubewellinfocollection.POJO.UserRegistrationInformation;
import com.example.tubewellinfocollection.R;
import com.example.tubewellinfocollection.Service.ApiService;
import com.example.tubewellinfocollection.Service.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "RegistrationActivity";
    private EditText etUserName, etOrgnaizationName, etMobileNumber, etEmail, etPassword;
    private Button btnRegistration;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
    }

    private void init() {
        apiService = ApiUtils.getAPIService();

        etUserName = findViewById(R.id.etUserEmail);
        etOrgnaizationName = findViewById(R.id.etOrgnaizationName);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegistration = findViewById(R.id.btnRegistration);
        btnRegistration.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnRegistration:
                try{
                    UserRegistrationInformation userRegistrationInformation = new UserRegistrationInformation();
                    userRegistrationInformation.setUserName(String.valueOf(etUserName.getText()));
                    userRegistrationInformation.setOrganizationName(String.valueOf(etOrgnaizationName.getText()));
                    userRegistrationInformation.setMobileNumber(String.valueOf(etMobileNumber.getText()));
                    userRegistrationInformation.setEmail(String.valueOf(etEmail.getText()));
                    userRegistrationInformation.setPassword(String.valueOf(etPassword.getText()));
                    apiService.registration(userRegistrationInformation).enqueue(new Callback<UserRegistrationResponse>() {
                        @Override
                        public void onResponse(Call<UserRegistrationResponse> call, Response<UserRegistrationResponse> response) {
                            if (response.code() == 200){
                                UserRegistrationResponse userRegistrationResponse = response.body();
                                Log.d(TAG, "onResponse: " + userRegistrationResponse.getResponse());
                            }
                        }

                        @Override
                        public void onFailure(Call<UserRegistrationResponse> call, Throwable t) {

                        }
                    });
                    Toast.makeText(getApplicationContext(), R.string.after_registration_text, Toast.LENGTH_LONG).show();
                    intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
        }
    }
}