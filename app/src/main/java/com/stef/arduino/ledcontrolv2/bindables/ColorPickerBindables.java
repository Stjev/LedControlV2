package com.stef.arduino.ledcontrolv2.bindables;

import android.databinding.BindingAdapter;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;

public class ColorPickerBindables {

    /**
     * This is the bindable property for the color picker to set the selected color listener
     * @param view the color picker view
     * @param listener the listener
     */
    @BindingAdapter("selectedColorListener")
    public static void setSelectedColorListener(ColorPickerView view, OnColorChangedListener listener) {
        view.addOnColorChangedListener(listener);
    }
}
