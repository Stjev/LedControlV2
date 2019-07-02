package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.stef.arduino.ledcontrolv2.abstract_classes.BluetoothDataViewModel;

public class StarViewModel extends BluetoothDataViewModel {
    private MutableLiveData<Integer> starCount;
    private MutableLiveData<Integer> starSpeed;

    public StarViewModel() {
        starCount = new MutableLiveData<>();
        starSpeed = new MutableLiveData<>();
        starCount.setValue(15); // Default value is 15
        starSpeed.setValue(50); // Default value is 50%
    }

    public LiveData<Integer> getStarCount() {
        return this.starCount;
    }

    public LiveData<Integer> getStarSpeed() {
        return this.starSpeed;
    }

    public void setStarCount(int starCount) {
        this.starCount.setValue(starCount);
        this.sendData();
    }

    public void setStarSpeed(int starSpeed) {
        this.starSpeed.setValue(starSpeed);
        this.sendData();
    }

    @Override
    public Byte[] getDataBytes() {
        byte count = (byte)(starCount.getValue() & 0xFF);
        byte speed = (byte)(starSpeed.getValue() & 0xFF);

        return new Byte[] {count, speed};
    }
}
