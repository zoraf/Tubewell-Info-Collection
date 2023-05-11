package com.example.tubewellinfocollection;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.util.Calendar;

public class TubeWellInformation extends AppCompatActivity implements View.OnClickListener {

    Group ownerTypeCardGroup;
    private TextInputLayout etOwnerName;
    private String[] ownerTypes;
    private String[] purposeOfUse;
    private String[] tubewellType;
    private String[] modeOfAbstraction;
    private LinearLayout ownerTypeCollapsibleContent, llOwnerType, llPurposeOfUse, purposeOfUseCollapsibleContent,
            llTubewellType, tubewellTypeCollapsibleContent, llbstractionType, abstractionTypeCollapsibleContent;
    private MaterialCardView cvOwnerType, cvPurposeOfUse, cvTubewellType, cvAbstractionType;

    private ShapeableImageView ivOwnerTypeDropDown, ivPurposeOfUseDropDown, ivTubewellTypeDropDown, ivAbstractionTypeDropDown;

    private Button btnInstallationDatePicker;
    private LocalDate installationDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tube_well_information);
        init();
    }

    private void init() {
        etOwnerName = findViewById(R.id.etOwnerName);
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

    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        BengaliDatePickerDialog datePickerDialog = new BengaliDatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Handle the selected date
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            installationDate = LocalDate.of(year, month, dayOfMonth);
                        }
                        Log.d("Date", String.valueOf(installationDate));
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

        }
        else if (view.getId() == llPurposeOfUse.getId()) {
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
        }
        else if (view.getId() == llTubewellType.getId()) {
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

        }
        else if (view.getId() == llbstractionType.getId()) {
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

        }
        else if (view.getId() == btnInstallationDatePicker.getId()) {
            showDatePickerDialog();
        }
    }
}