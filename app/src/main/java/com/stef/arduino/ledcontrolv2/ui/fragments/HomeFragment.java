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
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.stef.arduino.ledcontrolv2.enums.LedMode;
import com.stef.arduino.ledcontrolv2.R;
import com.stef.arduino.ledcontrolv2.databinding.FragmentHomeBinding;
import com.stef.arduino.ledcontrolv2.navigation.Navigator;
import com.stef.arduino.ledcontrolv2.viewmodels.BluetoothViewModel;
import com.stef.arduino.ledcontrolv2.viewmodels.GeneralOptionsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private GeneralOptionsViewModel generalOptionsViewModel;
    private BluetoothViewModel bluetoothViewModel;
    private boolean initailizing;

    public HomeFragment() {
        // Required empty public constructor
        initailizing = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        generalOptionsViewModel = ViewModelProviders.of(getActivity()).get(GeneralOptionsViewModel.class);
        generalOptionsViewModel.setActivity(getActivity());
        bluetoothViewModel = ViewModelProviders.of(getActivity()).get(BluetoothViewModel.class);

        // Set the bindings
        binding.setSpinnerModes(LedMode.getStringValues());
        binding.setSelectListener(itemSelectedListener);
        binding.setOnOffListener(onOffListener);
        binding.setBrightnessListener(brightnessListener);
        binding.setRetryConnectionListener(retryConnectionListener);

        bluetoothViewModel.initialize(getActivity(), this);
        bluetoothViewModel.getConnected().observe(this, binding::setIsConnected);
        bluetoothViewModel.getError().observe(this, binding::setErrorMessage);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    /**
     * This will be the Listener for the spinner
     */
    private AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            LedMode mode = LedMode.values()[position];

            if(initailizing) {
                initailizing = false;
            } else {
                if(generalOptionsViewModel != null)
                    generalOptionsViewModel.setLedMode(mode);

                if(getActivity() != null)
                    Navigator.navigateToOption(mode, getActivity());
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            parent.setSelection(LedMode.getInitialValue().ordinal());
        }
    };

    /**
     * This will listen for the on off button
     */
    private CompoundButton.OnCheckedChangeListener onOffListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(generalOptionsViewModel != null)
                generalOptionsViewModel.setOnOff(isChecked);
        }
    };

    /**
     * This will listen for the brightness changes
     */
    private SeekBar.OnSeekBarChangeListener brightnessListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if(generalOptionsViewModel != null)
                generalOptionsViewModel.setBrightness(seekBar.getProgress());
        }
    };

    /**
     * This is the listener for whenever the bluetooth connection has failed and the user clicks on the try
     * again button
     */
    private View.OnClickListener retryConnectionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(bluetoothViewModel != null)
                bluetoothViewModel.tryAgain();
        }
    };
}