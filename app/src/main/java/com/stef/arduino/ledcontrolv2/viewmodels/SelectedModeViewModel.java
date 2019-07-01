package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.stef.arduino.ledcontrolv2.LedMode;

public class SelectedModeViewModel extends ViewModel {
    private MutableLiveData<LedMode> ledMode;

    public SelectedModeViewModel() {
        this.ledMode = new MutableLiveData<>();
        this.ledMode.setValue(LedMode.COLOR_CYCLE); // Initially set Color cycle mode
    }

    public void setLedMode(LedMode ledMode) {
        System.out.println(ledMode);
        this.ledMode.setValue(ledMode);
    }

    public LedMode getLedMode() {
        return this.ledMode.getValue();
    }

    public LiveData<LedMode> getLedModeLiveData() {
        return this.ledMode;
    }
}
