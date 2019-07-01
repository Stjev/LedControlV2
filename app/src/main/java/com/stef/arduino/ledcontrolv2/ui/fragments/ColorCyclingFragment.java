package com.stef.arduino.ledcontrolv2.ui.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.stef.arduino.ledcontrolv2.R;
import com.stef.arduino.ledcontrolv2.databinding.FragmentColorCyclingBinding;
import com.stef.arduino.ledcontrolv2.viewmodels.ColorCycleOptionsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorCyclingFragment extends Fragment {

    private ColorCycleOptionsViewModel colorCycleOptionsViewModel;

    public ColorCyclingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentColorCyclingBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_color_cycling, container, false);

        colorCycleOptionsViewModel = ViewModelProviders.of(getActivity()).get(ColorCycleOptionsViewModel.class);

        binding.setSpeedListener(speedListener);
        binding.setProgressChangedListener((SeekBar seekBar, int progress, boolean fromUser) -> binding.setSpeed(progress));

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    /**
     * This is the listener for the speed option seekbar
     */
    private SeekBar.OnSeekBarChangeListener speedListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if(colorCycleOptionsViewModel != null)
                colorCycleOptionsViewModel.setSpeed(seekBar.getProgress());
        }
    };
}
