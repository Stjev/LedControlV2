package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.stef.arduino.ledcontrolv2.abstract_classes.BluetoothDataViewModel;

public class ColorCycleOptionsViewModel extends BluetoothDataViewModel {
    private MutableLiveData<Integer> speed;

    public ColorCycleOptionsViewModel() {
        speed = new MutableLiveData<>();
        speed.setValue(50);
    }

    public void setSpeed(Integer speed) {
        this.speed.setValue(speed);
        this.sendData();
    }

    public LiveData<Integer> getSpeed() {
        return speed;
    }

    @Override
    public byte[] getDataBytes() {
        return new byte[] {(byte) (speed.getValue() & 0xFF)};
    }
}
