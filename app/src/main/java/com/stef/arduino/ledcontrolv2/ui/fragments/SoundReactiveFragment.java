package com.stef.arduino.ledcontrolv2.ui.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stef.arduino.ledcontrolv2.R;
import com.stef.arduino.ledcontrolv2.databinding.FragmentSoundReactiveBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SoundReactiveFragment extends Fragment {


    public SoundReactiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentSoundReactiveBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_sound_reactive, container, false);

        

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

}
