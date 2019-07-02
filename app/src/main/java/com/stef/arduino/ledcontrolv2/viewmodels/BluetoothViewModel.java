package com.stef.arduino.ledcontrolv2.viewmodels;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.stef.arduino.ledcontrolv2.bluetooth.Bluetooth;

public class BluetoothViewModel extends ViewModel {
    private LiveData<Boolean> connected;
    private LiveData<String> error;
    private Bluetooth bluetoothRepo;

    public void startListeningForConnection(Activity activity, LifecycleOwner owner) {
        if(bluetoothRepo == null) bluetoothRepo = Bluetooth.getInstance(activity);

        connected = bluetoothRepo.getIsConnected();
        error = bluetoothRepo.getHasError();
    }

    public LiveData<Boolean> getConnected() {
        return connected;
    }

    public LiveData<String> getError() {
        return error;
    }

    /**
     * This will try to connect to the arduino again
     */
    public void tryAgain() {
        bluetoothRepo.startThreadAndConnect();
    }
}
