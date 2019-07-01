package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.stef.arduino.ledcontrolv2.enums.SoundReactiveMode;

public class SoundReactiveViewModel extends ViewModel {
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
    }

    public void setColor(Integer color) {
        this.color.setValue(color);
    }
}
