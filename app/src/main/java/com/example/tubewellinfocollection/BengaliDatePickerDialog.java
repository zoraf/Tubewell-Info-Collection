package com.example.tubewellinfocollection;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BengaliDatePickerDialog extends DatePickerDialog {

    private Locale bnLocale;
    public BengaliDatePickerDialog(@NonNull Context context, @Nullable OnDateSetListener listener, int year, int month, int dayOfMonth) {
        super(context, listener, year, month, dayOfMonth);

        bnLocale = new Locale("bn");
    }

    private String getFormattedDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        // Format the date and month names in Bengali
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", bnLocale);
        String date = dateFormat.format(calendar.getTime());

        return date;
    }

    @Override
    public void onDateChanged(@NonNull DatePicker view, int year, int month, int dayOfMonth) {
        super.onDateChanged(view, year, month, dayOfMonth);

        setTitle(getFormattedDate(year, month, dayOfMonth));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getFormattedDate(getDatePicker().getYear(), getDatePicker().getMonth(), getDatePicker().getDayOfMonth()));
    }

    @Override
    public void show() {
        super.show();

        // Set the date and month names in Bengali
        setTitle(getFormattedDate(getDatePicker().getYear(), getDatePicker().getMonth(), getDatePicker().getDayOfMonth()));
    }
}
