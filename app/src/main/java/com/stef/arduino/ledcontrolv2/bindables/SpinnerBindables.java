package com.stef.arduino.ledcontrolv2.bindables;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.stef.arduino.ledcontrolv2.R;

public class SpinnerBindables {
    /**
     * BindingAdapter for the data attribute from the spinner. This will set the data in the spinner
     * @param view The spinner
     * @param data A string array with the values for the spinner
     */
    @BindingAdapter({"data", "selectedPosition"})
    public static void setData(@NonNull Spinner view, String[] data, @Nullable Integer pos) {
        view.setAdapter(new ArrayAdapter<>(view.getContext(), R.layout.spinner_cell, data));
        if(pos != null) view.setSelection(pos);
    }

    /**
     * BindingAdapter for the itemSelectedListener for the spinner. This will set the itemSelectedListener used
     * by the itemSelectedListener
     * @param view The spinner
     * @param listener The itemSelectedListener to use when an item is selected
     */
    @BindingAdapter("itemSelectedListener")
    public static void setItemSelectedListener(Spinner view, AdapterView.OnItemSelectedListener listener) {
        view.setOnItemSelectedListener(listener);
    }
}
