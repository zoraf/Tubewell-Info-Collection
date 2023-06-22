package com.example.tubewellinfocollection.Activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.tubewellinfocollection.BengaliDatePickerDialog;
import com.example.tubewellinfocollection.POJO.LoginResponse;
import com.example.tubewellinfocollection.POJO.TubewellInformation;
import com.example.tubewellinfocollection.POJO.TubewellInformationSubmissionResponse;
import com.example.tubewellinfocollection.R;
import com.example.tubewellinfocollection.Service.ApiService;
import com.example.tubewellinfocollection.Service.ApiUtils;
import com.example.tubewellinfocollection.util.Constant;
import com.example.tubewellinfocollection.util.ConvertToBengali;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TubewellInformationCollectionActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    public static String TAG = "TubewellInformationCollectionActivity";
    public static int REQUEST_CODE = 1;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private TextInputLayout etOwnerName, etApprovalAuthorityName, etNoOfUser, etWaterUsage, etDepthOfPipe;
    private RadioGroup rgOwnerType, rgIsApprovedType, rgTubewellType, rgAbstractionType;
    private RadioButton rbIsApprovedType1, rbIsApprovedType2;
    private LinearLayout ownerTypeCollapsibleContent, llOwnerType, llPurposeOfUse, purposeOfUseCollapsibleContent,
            llTubewellType, tubewellTypeCollapsibleContent, llbstractionType, llIsApproved, isApprovedTypeCollapsibleContent, llApprovalAuthorityName, abstractionTypeCollapsibleContent;
    private MaterialCardView cvOwnerType, cvPurposeOfUse, cvTubewellType, cvAbstractionType, cvIsApproved;

    private ShapeableImageView ivOwnerTypeDropDown, ivPurposeOfUseDropDown, ivTubewellTypeDropDown, ivAbstractionTypeDropDown, ivIsApprovedDropDown;

    private Button btnInstallationDatePicker, btnSubmit, btnLastDateOfClearanceDatePicker;

    private MaterialCheckBox cbPurposeOfUse1, cbPurposeOfUse2, cbPurposeOfUse3, cbPurposeOfUse4, cbPurposeOfUse5, cbPurposeOfUse6, cbPurposeOfUse7,
            cbPurposeOfUse8, cbPurposeOfUse9, cbPurposeOfUse10, cbPurposeOfUse11, cbPurposeOfUse12;
    private LocalDate installationDate, lastDateOfClearance;

    private TextView tvInstallationDate, tvLastApprovalDate;

    private String title, bengaliDate;
    private ApiService apiService;
    private String Latitude, Longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tubewell_information_collection);
        getPermission();
        init();
    }

    private void getPermission() {
        List<String> permissionsList = new ArrayList<>();
        permissionsList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissionsList.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        List<String> permissionsToRequest = new ArrayList<>();

        for (String permission : permissionsList) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(permission);
            }
        }

        if (!permissionsToRequest.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest.toArray(new String[0]), REQUEST_CODE);
        }
    }

    private void init() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String locationToast = "Latitude:: " + location.getLatitude() + "Longitude::" + location.getLongitude();
                Log.d("debug", "onLocationChanged: " + locationToast);
                Toast.makeText(getApplicationContext(), locationToast, Toast.LENGTH_LONG).show();

                Latitude = String.valueOf(location.getLatitude());
                Longitude = String.valueOf(location.getLongitude());
                locationManager.removeUpdates(locationListener);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        apiService = ApiUtils.getAPIService();

        etOwnerName = findViewById(R.id.etOwnerName);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        llOwnerType = findViewById(R.id.llOwnerType);
        ivOwnerTypeDropDown = findViewById(R.id.ivOwnerTypeDropDown);
        cvOwnerType = findViewById(R.id.cvOwnerType);
        ownerTypeCollapsibleContent = findViewById(R.id.ownerTypeCollapsibleContent);
        llOwnerType.setOnClickListener(this);

        cvPurposeOfUse = findViewById(R.id.cvPurposeOfUse);
        llPurposeOfUse = findViewById(R.id.llPurposeOfUse);
        purposeOfUseCollapsibleContent = findViewById(R.id.purposeOfUseCollapsibleContent);
        ivPurposeOfUseDropDown = findViewById(R.id.ivPurposeOfUseDropDown);
        llPurposeOfUse.setOnClickListener(this);

        btnInstallationDatePicker = findViewById(R.id.btnInstallationDatePicker);
        btnInstallationDatePicker.setOnClickListener(this);

        cvTubewellType = findViewById(R.id.cvTubewellType);
        tubewellTypeCollapsibleContent = findViewById(R.id.tubewellTypeCollapsibleContent);
        ivTubewellTypeDropDown = findViewById(R.id.ivTubewellTypeDropDown);
        llTubewellType = findViewById(R.id.llTubewellType);
        llTubewellType.setOnClickListener(this);

        cvAbstractionType = findViewById(R.id.cvAbstractionType);
        abstractionTypeCollapsibleContent = findViewById(R.id.abstractionTypeCollapsibleContent);
        ivAbstractionTypeDropDown = findViewById(R.id.ivTubewellTypeDropDown);
        llbstractionType = findViewById(R.id.llbstractionType);
        llbstractionType.setOnClickListener(this);

        cvIsApproved = findViewById(R.id.cvIsApproved);
        isApprovedTypeCollapsibleContent = findViewById(R.id.isApprovedTypeCollapsibleContent);
        ivIsApprovedDropDown = findViewById(R.id.ivIsApprovedDropDown);
        llIsApproved = findViewById(R.id.llIsApproved);
        llIsApproved.setOnClickListener(this);
        llApprovalAuthorityName = findViewById(R.id.llApprovalAuthorityName);

        rgOwnerType = findViewById(R.id.rgOwnerType);
        rgOwnerType.setOnCheckedChangeListener(this);

        rgIsApprovedType = findViewById(R.id.rgIsApprovedType);
        rgIsApprovedType.setOnCheckedChangeListener(this);

        rgTubewellType = findViewById(R.id.rgTubewellType);
        rgTubewellType.setOnCheckedChangeListener(this);

        rgAbstractionType = findViewById(R.id.rgAbstractionType);
        rgAbstractionType.setOnCheckedChangeListener(this);

        tvInstallationDate = findViewById(R.id.tvInstallationDate);
        tvLastApprovalDate = findViewById(R.id.tvLastApprovalDate);

        btnLastDateOfClearanceDatePicker = findViewById(R.id.btnLastDateOfClearanceDatePicker);
        btnLastDateOfClearanceDatePicker.setOnClickListener(this);


        etApprovalAuthorityName = findViewById(R.id.etApprovalAuthorityName);
        etNoOfUser = findViewById(R.id.etNoOfUser);
        etWaterUsage = findViewById(R.id.etWaterUsage);
        etDepthOfPipe = findViewById(R.id.etDepthOfPipe);

        cbPurposeOfUse1 = findViewById(R.id.cbPurposeOfUse1);
        cbPurposeOfUse2 = findViewById(R.id.cbPurposeOfUse2);
        cbPurposeOfUse3 = findViewById(R.id.cbPurposeOfUse3);
        cbPurposeOfUse4 = findViewById(R.id.cbPurposeOfUse4);
        cbPurposeOfUse5 = findViewById(R.id.cbPurposeOfUse5);
        cbPurposeOfUse6 = findViewById(R.id.cbPurposeOfUse6);
        cbPurposeOfUse7 = findViewById(R.id.cbPurposeOfUse7);
        cbPurposeOfUse8 = findViewById(R.id.cbPurposeOfUse8);
        cbPurposeOfUse9 = findViewById(R.id.cbPurposeOfUse9);
        cbPurposeOfUse10 = findViewById(R.id.cbPurposeOfUse10);
        cbPurposeOfUse11 = findViewById(R.id.cbPurposeOfUse11);
        cbPurposeOfUse12 = findViewById(R.id.cbPurposeOfUse12);

    }

    private void showDatePickerDialog(View view) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        View btnView = view;

        BengaliDatePickerDialog datePickerDialog = new BengaliDatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Handle the selected date
                        bengaliDate = ConvertToBengali.convertToBengali(dayOfMonth) + "-" + ConvertToBengali.convertToBengali(month) + "-" + ConvertToBengali.convertToBengali(year);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            if (btnView.getId() == R.id.btnInstallationDatePicker) {
                                installationDate = LocalDate.of(year, month, dayOfMonth);

                                tvInstallationDate.setText(bengaliDate);
                                Log.d("Date", String.valueOf(installationDate));
                            } else if (btnView.getId() == R.id.btnLastDateOfClearanceDatePicker) {
                                Log.d("ClearanceDatePicker", "onDateSet: ");
                                lastDateOfClearance = LocalDate.of(year, month, dayOfMonth);
                                tvLastApprovalDate.setText(bengaliDate);
                                Log.d("Date", String.valueOf(lastDateOfClearance));
                            }

                        }

                    }
                },
                year,
                month,
                dayOfMonth
        );
        datePickerDialog.setTitle("তারিখ নির্বাচন করুন");
        datePickerDialog.getDatePicker().setSpinnersShown(true);
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {

        try {
            if (view.getId() == llOwnerType.getId()) {
                TransitionManager.beginDelayedTransition(cvOwnerType, new AutoTransition());

                if (ownerTypeCollapsibleContent.getVisibility() == View.VISIBLE) {
                    Log.e("visibility", "visible");
                    ownerTypeCollapsibleContent.setVisibility(View.GONE);
                    ivOwnerTypeDropDown.setImageResource(android.R.drawable.arrow_down_float);
                } else {
                    Log.e("visibility", "gone");
                    ownerTypeCollapsibleContent.setVisibility(View.VISIBLE);
                    ivOwnerTypeDropDown.setImageResource(android.R.drawable.arrow_up_float);
                }

            } else if (view.getId() == llPurposeOfUse.getId()) {
                TransitionManager.beginDelayedTransition(cvPurposeOfUse, new AutoTransition());

                if (purposeOfUseCollapsibleContent.getVisibility() == View.VISIBLE) {
                    Log.e("visibility", "visible");
                    purposeOfUseCollapsibleContent.setVisibility(View.GONE);
                    ivPurposeOfUseDropDown.setImageResource(android.R.drawable.arrow_down_float);
                } else {
                    Log.e("visibility", "gone");
                    purposeOfUseCollapsibleContent.setVisibility(View.VISIBLE);
                    ivPurposeOfUseDropDown.setImageResource(android.R.drawable.arrow_up_float);
                }
            } else if (view.getId() == llTubewellType.getId()) {
                TransitionManager.beginDelayedTransition(cvTubewellType, new AutoTransition());

                if (tubewellTypeCollapsibleContent.getVisibility() == View.VISIBLE) {
                    Log.e("visibility", "visible");
                    tubewellTypeCollapsibleContent.setVisibility(View.GONE);
                    ivTubewellTypeDropDown.setImageResource(android.R.drawable.arrow_down_float);
                } else {
                    Log.e("visibility", "gone");
                    tubewellTypeCollapsibleContent.setVisibility(View.VISIBLE);
                    ivTubewellTypeDropDown.setImageResource(android.R.drawable.arrow_up_float);
                }

            } else if (view.getId() == llbstractionType.getId()) {
                TransitionManager.beginDelayedTransition(cvAbstractionType, new AutoTransition());

                if (abstractionTypeCollapsibleContent.getVisibility() == View.VISIBLE) {
                    Log.e("visibility", "visible");
                    abstractionTypeCollapsibleContent.setVisibility(View.GONE);
                    ivAbstractionTypeDropDown.setImageResource(android.R.drawable.arrow_down_float);
                } else {
                    Log.e("visibility", "gone");
                    abstractionTypeCollapsibleContent.setVisibility(View.VISIBLE);
                    ivAbstractionTypeDropDown.setImageResource(android.R.drawable.arrow_up_float);
                }

            } else if (view.getId() == llIsApproved.getId()) {
                TransitionManager.beginDelayedTransition(cvIsApproved, new AutoTransition());

                if (isApprovedTypeCollapsibleContent.getVisibility() == View.VISIBLE) {
                    Log.e("visibility", "visible");
                    isApprovedTypeCollapsibleContent.setVisibility(View.GONE);
                    ivIsApprovedDropDown.setImageResource(android.R.drawable.arrow_down_float);
                    llApprovalAuthorityName.setVisibility(View.GONE);
                } else {
                    Log.e("visibility", "gone");
                    isApprovedTypeCollapsibleContent.setVisibility(View.VISIBLE);
                    ivIsApprovedDropDown.setImageResource(android.R.drawable.arrow_up_float);

                }
            } else if (view.getId() == btnInstallationDatePicker.getId()) {
                showDatePickerDialog(view);
            } else if (view.getId() == btnLastDateOfClearanceDatePicker.getId()) {
                showDatePickerDialog(view);
            } else if (view.getId() == btnSubmit.getId()) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Log.d(TAG, "onClick: " + "button is clicked");
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                sendTubewellInformation();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void sendTubewellInformation() {
        TubewellInformation tubewellInformation = new TubewellInformation();
        tubewellInformation.setOwnerName(etOwnerName.getEditText().getText().toString());

        RadioButton selectedOwnerType = findViewById(rgOwnerType.getCheckedRadioButtonId());
        if (selectedOwnerType != null) {
            tubewellInformation.setOwnerType(selectedOwnerType.getText().toString());
        }

        tubewellInformation.setDateOfInstallation(tvInstallationDate.getText().toString());

        RadioButton selectedIsApprovalTaken = findViewById(rgIsApprovedType.getCheckedRadioButtonId());
        if (selectedIsApprovalTaken != null) {
            tubewellInformation.setIsApprovalTaken(selectedIsApprovalTaken.getText().toString());
        }

        tubewellInformation.setApprovalAuthority(etApprovalAuthorityName.getEditText().getText().toString());
        tubewellInformation.setLastApprovalDate(tvLastApprovalDate.getText().toString());

        tubewellInformation.setNoOfUser(etNoOfUser.getEditText().getText().toString());

        List<String> purposeOfUsage = new ArrayList<String>();

        if (cbPurposeOfUse1.isChecked() == true)
            purposeOfUsage.add(cbPurposeOfUse1.getText().toString());
        if (cbPurposeOfUse2.isChecked() == true)
            purposeOfUsage.add(cbPurposeOfUse2.getText().toString());
        if (cbPurposeOfUse3.isChecked() == true)
            purposeOfUsage.add(cbPurposeOfUse3.getText().toString());
        if (cbPurposeOfUse4.isChecked() == true)
            purposeOfUsage.add(cbPurposeOfUse4.getText().toString());
        if (cbPurposeOfUse5.isChecked() == true)
            purposeOfUsage.add(cbPurposeOfUse5.getText().toString());
        if (cbPurposeOfUse6.isChecked() == true)
            purposeOfUsage.add(cbPurposeOfUse6.getText().toString());
        if (cbPurposeOfUse7.isChecked() == true)
            purposeOfUsage.add(cbPurposeOfUse7.getText().toString());
        if (cbPurposeOfUse8.isChecked() == true)
            purposeOfUsage.add(cbPurposeOfUse8.getText().toString());
        if (cbPurposeOfUse9.isChecked() == true)
            purposeOfUsage.add(cbPurposeOfUse9.getText().toString());
        if (cbPurposeOfUse10.isChecked() == true)
            purposeOfUsage.add(cbPurposeOfUse10.getText().toString());
        if (cbPurposeOfUse11.isChecked() == true)
            purposeOfUsage.add(cbPurposeOfUse11.getText().toString());
        if (cbPurposeOfUse12.isChecked() == true)
            purposeOfUsage.add(cbPurposeOfUse12.getText().toString());

        tubewellInformation.setPurposeOfUsage(purposeOfUsage);

        tubewellInformation.setAmountOfWaterUse(etWaterUsage.getEditText().getText().toString());


        RadioButton selectedTubewellType = findViewById(rgTubewellType.getCheckedRadioButtonId());
        if (selectedTubewellType != null) {
            tubewellInformation.setTubewellType(selectedTubewellType.getText().toString());
        }

        RadioButton selectedAbstractionType = findViewById(rgAbstractionType.getCheckedRadioButtonId());
        if (selectedAbstractionType != null) {
            tubewellInformation.setModeOfAbstraction(selectedAbstractionType.getText().toString());
        }

        tubewellInformation.setLengthOfPipeUsed(etDepthOfPipe.getEditText().getText().toString());

        tubewellInformation.setLatitude(Latitude);
        tubewellInformation.setLongitude(Longitude);
        apiService.submitTubewellInformation(tubewellInformation).enqueue(new Callback<TubewellInformationSubmissionResponse>() {
            @Override
            public void onResponse(Call<TubewellInformationSubmissionResponse> call, Response<TubewellInformationSubmissionResponse> response) {

                if (response.code() == 200) {
                    TubewellInformationSubmissionResponse tubewellInformationSubmissionResponse = response.body();
                    if (tubewellInformationSubmissionResponse.getResponseCode() == Constant.SUBMIT_TUBEWELL_INFORMATION_SUCCESSFUL) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<TubewellInformationSubmissionResponse> call, Throwable t) {

            }
        });

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group.getId() == rgIsApprovedType.getId()) {
            switch (checkedId) {
                case R.id.rbIsApprovedType1:
                    llApprovalAuthorityName.setVisibility(View.VISIBLE);
                    break;
                case R.id.rbIsApprovedType2:
                    llApprovalAuthorityName.setVisibility(View.GONE);
                    break;
            }
        }
    }
}