package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ColorCycleOptionsViewModel extends ViewModel {
    private MutableLiveData<Integer> speed;

    public ColorCycleOptionsViewModel() {
        speed = new MutableLiveData<>();
        speed.setValue(50);
    }

    public void setSpeed(Integer speed) {
        this.speed.setValue(speed);
        System.out.println(speed);
    }

    public LiveData<Integer> getSpeed() {
        return speed;
    }
}
