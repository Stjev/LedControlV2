package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;

import com.stef.arduino.ledcontrolv2.enums.LedMode;
import com.stef.arduino.ledcontrolv2.abstract_classes.BluetoothDataViewModel;

public class GeneralOptionsViewModel extends BluetoothDataViewModel {
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
        this.sendData();
    }

    public void setBrightness(Integer brightness) {
        this.brightness.setValue(this.ledsOn ? brightness : 0);
        this.lastBrightness = brightness;
        this.sendData();
    }

    // The on off switch will just set the brightness to 0 or the value of the seekbar
    public void setOnOff(Boolean onOff) {
        this.brightness.setValue(onOff ? lastBrightness : 0);
        this.ledsOn = onOff;
        this.sendData();
    }

    @Override
    public void sendData() {
        BluetoothDataViewModel specificDataModel = null;

        switch (this.ledMode.getValue()) {
            case SOUND_REACTIVE:
                specificDataModel = ViewModelProviders.of(getActivity()).get(SoundReactiveViewModel.class);
                break;
            case STARS:
                specificDataModel = ViewModelProviders.of(getActivity()).get(StarViewModel.class);
                break;
            case STATIC_COLOR:
                specificDataModel = ViewModelProviders.of(getActivity()).get(StaticViewModel.class);
                break;
            case COLOR_CYCLE:
                specificDataModel = ViewModelProviders.of(getActivity()).get(ColorCycleOptionsViewModel.class);
                break;
        }

        getBluetoothViewModel().sendData(getActivity(), specificDataModel);
    }

    public LiveData<Integer> getBrightness() {
        return this.brightness;
    }

    public LiveData<LedMode> getLedMode() {
        return this.ledMode;
    }

    @Override
    public Byte[] getDataBytes() {
        byte mode = (byte)ledMode.getValue().ordinal();
        byte brightness = (byte) (this.brightness.getValue() & 0xFF);

        return new Byte[] {mode, brightness};
    }
}
