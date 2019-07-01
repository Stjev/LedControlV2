package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class StaticViewModel extends ViewModel {
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
}
