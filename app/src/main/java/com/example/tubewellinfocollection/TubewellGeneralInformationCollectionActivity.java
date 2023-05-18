package com.example.tubewellinfocollection;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
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
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.Group;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.tubewellinfocollection.util.ConvertToBengali;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TubewellGeneralInformationCollectionActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    public static String TAG = "TubewellGeneralInformationCollectionActivity";
    public static int REQUEST_CODE = 1;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private TextInputLayout etOwnerName;
    private RadioGroup rgOwnerType, rgIsApprovedType, rgTubewellType, rgAbstractionType;
    private RadioButton rbIsApprovedType1, rbIsApprovedType2;
    private LinearLayout ownerTypeCollapsibleContent, llOwnerType, llPurposeOfUse, purposeOfUseCollapsibleContent,
            llTubewellType, tubewellTypeCollapsibleContent, llbstractionType, llIsApproved, isApprovedTypeCollapsibleContent, llApprovalAuthorityName, abstractionTypeCollapsibleContent;
    private MaterialCardView cvOwnerType, cvPurposeOfUse, cvTubewellType, cvAbstractionType, cvIsApproved;

    private ShapeableImageView ivOwnerTypeDropDown, ivPurposeOfUseDropDown, ivTubewellTypeDropDown, ivAbstractionTypeDropDown, ivIsApprovedDropDown;

    private Button btnInstallationDatePicker, btnSubmit, btnLastDateOfClearanceDatePicker;
    private LocalDate installationDate, lastDateOfClearance;

    private TextView tvInstallationDate, tvLastApprovalDate;

    private String title, bengaliDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tubewell_general_information_collection);
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = getResources().getString(R.string.title);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setIcon(R.drawable.baseline_water_drop_24);

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
            TubewellInformation information = new TubewellInformation();
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
        }
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