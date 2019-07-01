package com.stef.arduino.ledcontrolv2.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.stef.arduino.ledcontrolv2.LedMode;
import com.stef.arduino.ledcontrolv2.R;
import com.stef.arduino.ledcontrolv2.databinding.FragmentHomeBinding;
import com.stef.arduino.ledcontrolv2.viewmodels.SelectedModeViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private SelectedModeViewModel selectedModeViewModel;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        selectedModeViewModel = ViewModelProviders.of(getActivity()).get(SelectedModeViewModel.class);

        // Set the led modes
        binding.setSpinnerModes(LedMode.getStringValues());
        binding.setSelectListener(listener);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    /**
     * This will be the Listener for the spinner
     */
    private AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(selectedModeViewModel != null)
                selectedModeViewModel.setLedMode(LedMode.values()[position]);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            parent.setSelection(LedMode.COLOR_CYCLE.ordinal());
        }
    };


    /**
     * BindingAdapter for the data attribute from the spinner. This will set the data in the spinner
     * @param view The spinner
     * @param data A string array with the values for the spinner
     */
    @BindingAdapter("data")
    public static void setData(Spinner view, String[] data) {
        view.setAdapter(new ArrayAdapter<>(view.getContext(), R.layout.spinner_cell, data));
    }

    /**
     * BindingAdapter for the itemSelectedListener for the spinner. This will set the listener used
     * by the listener
     * @param view The spinner
     * @param listener The listener to use when an item is selected
     */
    @BindingAdapter("itemSelectedListener")
    public static void setItemSelectedListener(Spinner view, AdapterView.OnItemSelectedListener listener) {
        view.setOnItemSelectedListener(listener);
    }
}
