package com.npkompleet.phenomenon.faultrank;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;

import java.util.Calendar;

/**
 * Created by PHENOMENON on 7/16/2017.
 */

public class DatePickerFragment extends AppCompatDialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getContext(),
                (DatePickerDialog.OnDateSetListener) getTargetFragment(), year, month, day);
    }
}
