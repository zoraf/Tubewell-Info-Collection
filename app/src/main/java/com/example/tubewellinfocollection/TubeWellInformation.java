package com.example.tubewellinfocollection;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputLayout;

public class TubeWellInformation extends AppCompatActivity {

    Group ownerTypeCardGroup;
    private TextInputLayout etOwnerName;
    private String[] ownerTypes;
    private String[] purposeOfUse;
    private String[] tubewellType;
    private String[] modeOfAbstraction;
    private LinearLayout ownerTypeCheckBoxContainer, ownerTypeCollapsibleContent;
    private MaterialCardView cvOwnerType;
    private ConstraintLayout onwerTypeCollapsibleContent;
    private ShapeableImageView ivOwnerTypeDropDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tube_well_information);
        init();
    }

    private void init() {
        etOwnerName = findViewById(R.id.etOwnerName);
        ivOwnerTypeDropDown = findViewById(R.id.ivOwnerTypeDropDown);
        ownerTypeCardGroup = findViewById(R.id.ownerTypeCardGroup);
        cvOwnerType = findViewById(R.id.cvOwnerType);
        ownerTypeCollapsibleContent = findViewById(R.id.ownerTypeCollapsibleContent);
        ivOwnerTypeDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });


    }
}