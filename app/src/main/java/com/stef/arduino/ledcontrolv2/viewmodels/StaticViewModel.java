package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.stef.arduino.ledcontrolv2.interfaces.BluetoothDataViewModel;

public class StaticViewModel extends ViewModel implements BluetoothDataViewModel {
    private MutableLiveData<Integer> color;

    public StaticViewModel() {
        this.color = new MutableLiveData<>();
        this.color.setValue(-1);
    }

    public void setColor(Integer color) {
        this.color.setValue(color);
    }

    public LiveData<Integer> getColor() {
        return this.color;
    }

    @Override
    public Byte[] getDataBytes() {
        //TODO: Implement

        return new Byte[0];
    }
}
