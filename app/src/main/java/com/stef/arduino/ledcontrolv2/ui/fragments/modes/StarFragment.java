package com.stef.arduino.ledcontrolv2.ui.fragments.modes;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.stef.arduino.ledcontrolv2.R;
import com.stef.arduino.ledcontrolv2.databinding.FragmentStarBinding;
import com.stef.arduino.ledcontrolv2.viewmodels.BluetoothViewModel;
import com.stef.arduino.ledcontrolv2.viewmodels.StarViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class StarFragment extends Fragment {

    private StarViewModel starViewModel;

    public StarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentStarBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_star, container, false);

        starViewModel = ViewModelProviders.of(this).get(StarViewModel.class);
        starViewModel.setActivity(getActivity());

        // Set the bindings
        binding.setSpeed(starViewModel.getStarSpeed().getValue());
        binding.setCount(starViewModel.getStarCount().getValue());
        binding.setCountChanged((SeekBar _s, int count, boolean _fu) -> binding.setCount(count));
        binding.setSpeedChanged((SeekBar _s, int speed, boolean _fu) -> binding.setSpeed(speed));
        binding.setCountListener(countListener);
        binding.setSpeedListener(speedListener);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    /**
     * This will control the changes in the speed seekbar
     */
    private SeekBar.OnSeekBarChangeListener speedListener = new SeekBar.OnSeekBarChangeListener() {
        @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}
        @Override public void onStartTrackingTouch(SeekBar seekBar) {}
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if(starViewModel != null)
                starViewModel.setStarSpeed(seekBar.getProgress());
        }
    };

    /**
     * This will control the changes in the star count seekbar
     */
    private SeekBar.OnSeekBarChangeListener countListener = new SeekBar.OnSeekBarChangeListener() {
        @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}
        @Override public void onStartTrackingTouch(SeekBar seekBar) {}
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if(starViewModel != null)
                starViewModel.setStarCount(seekBar.getProgress());
        }
    };
}
