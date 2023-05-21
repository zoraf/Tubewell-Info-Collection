package com.example.tubewellinfocollection.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tubewellinfocollection.POJO.UserInformation;
import com.example.tubewellinfocollection.R;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUserName,etOrgnaizationName,etMobileNumber,etEmail,etPassword;
    private Button btnRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
    }

    private void init(){
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
        switch (view.getId()){
            case R.id.btnRegistration:
                UserInformation userInformation = new UserInformation();
                userInformation.setUserName(String.valueOf(etUserName.getText()));
                userInformation.setOrganizationName(String.valueOf(etOrgnaizationName.getText()));
                userInformation.setMobileNumber(String.valueOf(etMobileNumber.getText()));
                userInformation.setEmail(String.valueOf(etEmail.getText()));
                userInformation.setPassword(String.valueOf(etPassword.getText()));
                break;
        }
    }
}