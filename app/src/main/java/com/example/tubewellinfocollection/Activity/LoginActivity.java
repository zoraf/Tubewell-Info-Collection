package com.example.tubewellinfocollection.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tubewellinfocollection.POJO.LoginInformation;
import com.example.tubewellinfocollection.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    EditText etUserEmail, etPassword;
    Button btnLogin, btnRegistration;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                LoginInformation loginInformation = new LoginInformation();
                loginInformation.setUserEmail(String.valueOf(etUserEmail.getText()));
                loginInformation.setPassword(String.valueOf(etPassword.getText()));
                break;
            case R.id.btnRegistration:
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                break;
        }
    }
}