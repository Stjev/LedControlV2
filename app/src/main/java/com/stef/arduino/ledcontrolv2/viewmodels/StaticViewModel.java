package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.graphics.Color;

import com.stef.arduino.ledcontrolv2.abstract_classes.BluetoothDataViewModel;

public class StaticViewModel extends BluetoothDataViewModel {
    private MutableLiveData<Integer> color;

    public StaticViewModel() {
        this.color = new MutableLiveData<>();
        this.color.setValue(-1);
    }

    public void setColor(Integer color) {
        this.color.setValue(color);
        this.sendData();
    }

    public LiveData<Integer> getColor() {
        return this.color;
    }

    @Override
    public Byte[] getDataBytes() {
        Color color = Color.valueOf(this.color.getValue());

        byte red = (byte) ((int)color.red() & 0xFF);
        byte green = (byte) ((int)color.green() & 0xFF);
        byte blue = (byte) ((int)color.blue() & 0xFF);

        return new Byte[] {red, green, blue};
    }
}
