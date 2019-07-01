package com.stef.arduino.ledcontrolv2.ui.fragments.modes;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;
import com.stef.arduino.ledcontrolv2.enums.SoundReactiveMode;
import com.stef.arduino.ledcontrolv2.R;
import com.stef.arduino.ledcontrolv2.databinding.FragmentSoundReactiveBinding;
import com.stef.arduino.ledcontrolv2.viewmodels.SoundReactiveViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SoundReactiveFragment extends Fragment {

    private SoundReactiveViewModel soundReactiveViewModel;
    private int selectedColor;

    public SoundReactiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSoundReactiveBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_sound_reactive, container, false);

        soundReactiveViewModel = ViewModelProviders.of(getActivity()).get(SoundReactiveViewModel.class);

        // Bindings
        binding.setSoundReactiveModes(SoundReactiveMode.getStringValues());
        binding.setSelectListener(itemSelectedListener);
        binding.setSelectedMode(soundReactiveViewModel.getReactiveMode().getValue().ordinal());
        binding.setSelectedColorListener(selectedColorListener);
        binding.setButtonClickListener(clickListener);
        binding.setSelectedColor(soundReactiveViewModel.getColor().getValue());
        soundReactiveViewModel.getReactiveMode().observe(this, mode ->
            binding.setShowWheel(mode != null && mode.equals(SoundReactiveMode.BRIGHTNESS_MODE)));

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    /**
     * This will be the Listener for the spinner
     */
    private AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(soundReactiveViewModel != null)
                soundReactiveViewModel.setReactiveMode(SoundReactiveMode.values()[position]);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            parent.setSelection(SoundReactiveMode.getInitialValue().ordinal());
        }
    };

    /**
     * This will update the viewmodel with the new selected color.
     */
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(soundReactiveViewModel != null)
                soundReactiveViewModel.setColor(selectedColor);
        }
    };

    /**
     * This is the color listener for the color picker wheel
     */
    private OnColorChangedListener selectedColorListener = selectedColor -> this.selectedColor = selectedColor;
}
