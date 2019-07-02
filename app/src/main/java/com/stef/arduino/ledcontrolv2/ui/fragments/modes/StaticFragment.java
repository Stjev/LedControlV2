package com.stef.arduino.ledcontrolv2.ui.fragments.modes;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flask.colorpicker.OnColorChangedListener;
import com.stef.arduino.ledcontrolv2.R;
import com.stef.arduino.ledcontrolv2.databinding.FragmentStaticBinding;
import com.stef.arduino.ledcontrolv2.viewmodels.BluetoothViewModel;
import com.stef.arduino.ledcontrolv2.viewmodels.StaticViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class StaticFragment extends Fragment {

    private StaticViewModel staticViewModel;
    private Integer selectedColor;

    public StaticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentStaticBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_static, container, false);

        staticViewModel = ViewModelProviders.of(getActivity()).get(StaticViewModel.class);
        staticViewModel.setActivity(getActivity());

        binding.setSelectedColor(staticViewModel.getColor().getValue());
        binding.setSelectedColorListener(colorChangedListener);
        binding.setClickListener(clickListener);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    /**
     * This will update the color inside the viewmodel
     */
    private OnColorChangedListener colorChangedListener = selectedColor -> this.selectedColor = selectedColor;
    private View.OnClickListener clickListener = v -> {
        if(staticViewModel != null)
            staticViewModel.setColor(selectedColor);
    };
}
