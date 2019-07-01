package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.stef.arduino.ledcontrolv2.LedMode;

public class GeneralOptionsViewModel extends ViewModel {
    private MutableLiveData<LedMode> ledMode;
    private MutableLiveData<Integer> brightness;
    // Keep track of the current state
    private Integer lastBrightness;
    private boolean ledsOn;

    public GeneralOptionsViewModel() {
        this.ledMode = new MutableLiveData<>();
        this.brightness = new MutableLiveData<>();
        this.ledMode.setValue(LedMode.getInitialValue());
        this.brightness.setValue(0);

        this.lastBrightness = 255;
        this.ledsOn = false;
    }

    public void setLedMode(LedMode ledMode) {
        this.ledMode.setValue(ledMode);
    }

    public void setBrightness(Integer brightness) {
        this.brightness.setValue(this.ledsOn ? brightness : 0);
        this.lastBrightness = brightness;
    }

    // The on off switch will just set the brightness to 0 or the value of the seekbar
    public void setOnOff(Boolean onOff) {
        this.brightness.setValue(onOff ? lastBrightness : 0);
        this.ledsOn = onOff;
    }

    public LiveData<Integer> getBrightness() {
        return this.brightness;
    }

    public LiveData<LedMode> getLedMode() {
        return this.ledMode;
    }
}
