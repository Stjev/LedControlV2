package com.stef.arduino.ledcontrolv2.abstract_classes;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import com.stef.arduino.ledcontrolv2.viewmodels.BluetoothViewModel;

public abstract class BluetoothDataViewModel extends ViewModel {
    public abstract Byte[] getDataBytes();

    private BluetoothViewModel bluetoothViewModel;
    private FragmentActivity activity;

    public FragmentActivity getActivity() {
        return activity;
    }

    public BluetoothViewModel getBluetoothViewModel() {
        return bluetoothViewModel;
    }

    public void sendData() {
        bluetoothViewModel.sendData(activity, this);
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
        this.bluetoothViewModel = ViewModelProviders.of(activity).get(BluetoothViewModel.class);
    }
}
