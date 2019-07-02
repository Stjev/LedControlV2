package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.graphics.Color;

import com.stef.arduino.ledcontrolv2.enums.SoundReactiveMode;
import com.stef.arduino.ledcontrolv2.abstract_classes.BluetoothDataViewModel;

public class SoundReactiveViewModel extends BluetoothDataViewModel {
    private MutableLiveData<SoundReactiveMode> reactiveMode;
    private MutableLiveData<Integer> color;

    public SoundReactiveViewModel() {
        this.reactiveMode = new MutableLiveData<>();
        this.color = new MutableLiveData<>();
        this.reactiveMode.setValue(SoundReactiveMode.PROGRESSIVE_MODE);
        this.color.setValue(-1);
    }

    public LiveData<SoundReactiveMode> getReactiveMode() {
        return reactiveMode;
    }

    public LiveData<Integer> getColor() { return this.color; }

    public void setReactiveMode(SoundReactiveMode reactiveMode) {
        this.reactiveMode.setValue(reactiveMode);
        this.sendData();
    }

    public void setColor(Integer color) {
        this.color.setValue(color);
        this.sendData();
    }

    @Override
    public Byte[] getDataBytes() {
        byte mode = (byte) reactiveMode.getValue().ordinal();

        if(reactiveMode.getValue() == SoundReactiveMode.PROGRESSIVE_MODE) {
            return new Byte[] {mode};
        } else {
            Color color = Color.valueOf(this.color.getValue());
            byte red = (byte) ((int)color.red() & 0xFF);
            byte green = (byte) ((int)color.green() & 0xFF);
            byte blue = (byte) ((int)color.blue() & 0xFF);

            return new Byte[] {mode, red, green, blue};
        }
    }
}
