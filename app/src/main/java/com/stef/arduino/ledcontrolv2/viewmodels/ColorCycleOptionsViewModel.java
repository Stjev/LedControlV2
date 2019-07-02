package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.stef.arduino.ledcontrolv2.interfaces.BluetoothDataViewModel;

public class ColorCycleOptionsViewModel extends ViewModel implements BluetoothDataViewModel {
    private MutableLiveData<Integer> speed;

    public ColorCycleOptionsViewModel() {
        speed = new MutableLiveData<>();
        speed.setValue(50);
    }

    public void setSpeed(Integer speed) {
        this.speed.setValue(speed);
    }

    public LiveData<Integer> getSpeed() {
        return speed;
    }

    @Override
    public Byte[] getDataBytes() {
        return new Byte[] {(byte) (speed.getValue() & 0xFF)};
    }
}
