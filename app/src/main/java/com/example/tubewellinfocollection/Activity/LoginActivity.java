package com.example.tubewellinfocollection.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tubewellinfocollection.POJO.LoginInformation;
import com.example.tubewellinfocollection.POJO.LoginResponse;
import com.example.tubewellinfocollection.R;
import com.example.tubewellinfocollection.Service.ApiService;
import com.example.tubewellinfocollection.Service.ApiUtils;
import com.example.tubewellinfocollection.util.Constant;

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
        final Intent[] intent = new Intent[1];
        switch (view.getId()) {
            case R.id.btnLogin:
                try {
                    LoginInformation loginInformation = new LoginInformation();
                    loginInformation.setUserEmail(String.valueOf(etUserEmail.getText()));
                    loginInformation.setPassword(String.valueOf(etPassword.getText()));
                    apiService.login(loginInformation).enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.code() == 200) {
                                LoginResponse loginResponse = response.body();
                                Log.d(TAG, "onResponse: " + loginResponse.getResponse());
                                if (loginResponse.getResponseCode() == Constant.USER_NOT_FOUND) {
                                    Toast.makeText(getApplicationContext(), loginResponse.getResponse(), Toast.LENGTH_LONG).show();
                                    intent[0] = new Intent(LoginActivity.this, RegistrationActivity.class);
                                    startActivity(intent[0]);
                                } else if (loginResponse.getResponseCode() == Constant.USER_INACTIVE) {
                                    Toast.makeText(getApplicationContext(), loginResponse.getResponse(), Toast.LENGTH_LONG).show();
                                } else if (loginResponse.getResponseCode() == Constant.WRONG_PASSWORD) {
                                    Toast.makeText(getApplicationContext(), loginResponse.getResponse(), Toast.LENGTH_LONG).show();
                                } else {
                                    intent[0] = new Intent(LoginActivity.this, TubewellGeneralInformationCollectionActivity.class);
                                    intent[0].putExtra("userName", loginInformation.getUserEmail());
                                    startActivity(intent[0]);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), R.string.msg_login_failed, Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnRegistration:
                intent[0] = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent[0]);
                break;
        }
    }
}