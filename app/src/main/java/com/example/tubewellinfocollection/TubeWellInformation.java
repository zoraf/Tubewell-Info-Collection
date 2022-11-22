package com.example.tubewellinfocollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class TubeWellInformation extends AppCompatActivity {

    private EditText etOwnerName;
    private Spinner spnOwnerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tube_well_information);
        init();
    }

    private void init(){
        etOwnerName = findViewById(R.id.etOwnerName);
        spnOwnerType = findViewById(R.id.spnOwnerType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.owner_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOwnerType.setAdapter(adapter);
    }
}