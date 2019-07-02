package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

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
    public byte[] getDataBytes() {
        Integer color = this.color.getValue();
        byte red = (byte) ((color >> 16) & 0xFF);
        byte green = (byte) ((color >> 8) & 0xFF);
        byte blue = (byte) (color & 0xFF);

        return new byte[] {red, green, blue};
    }
}
