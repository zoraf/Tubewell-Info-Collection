package com.example.tubewellinfocollection.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tubewellinfocollection.POJO.LoginInformation;
import com.example.tubewellinfocollection.POJO.LoginResponse;
import com.example.tubewellinfocollection.R;
import com.example.tubewellinfocollection.Service.ApiService;
import com.example.tubewellinfocollection.Service.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "LoginActivity";

    private EditText etUserEmail, etPassword;
    private Button btnLogin, btnRegistration;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        etUserEmail = findViewById(R.id.etUserEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        btnRegistration = findViewById(R.id.btnRegistration);
        btnRegistration.setOnClickListener(this);

        apiService = ApiUtils.getAPIService();
    }

    @Override
    public void onClick(View view) {

        Intent intent;
        switch (view.getId()) {
            case R.id.btnLogin:
                try {

                    LoginInformation loginInformation = new LoginInformation();
                    loginInformation.setUserEmail(String.valueOf(etUserEmail.getText()));
                    loginInformation.setPassword(String.valueOf(etPassword.getText()));
                    apiService.login(loginInformation).enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if(response.code() == 200){
                                LoginResponse loginResponse = response.body();
                                Log.d(TAG, "onResponse: " + loginResponse.getResponse());
                            }

                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {

                        }
                    });
                    intent = new Intent(LoginActivity.this, TubewellGeneralInformationCollectionActivity.class);
                    intent.putExtra("userName", loginInformation.getUserEmail());
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnRegistration:
                intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                break;
        }
    }
}