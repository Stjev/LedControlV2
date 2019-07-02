package com.stef.arduino.ledcontrolv2.viewmodels;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.stef.arduino.ledcontrolv2.bluetooth.Bluetooth;
import com.stef.arduino.ledcontrolv2.bluetooth.BluetoothSender;
import com.stef.arduino.ledcontrolv2.abstract_classes.BluetoothDataViewModel;

import java.io.IOException;

public class BluetoothViewModel extends ViewModel {
    private LiveData<Boolean> connected;
    private LiveData<String> error;
    private Bluetooth bluetoothRepo;

    public void initialize(FragmentActivity activity) {
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

    public void sendData(FragmentActivity activity, BluetoothDataViewModel viewModel) {
        BluetoothSender sender = new BluetoothSender(activity);
        try {
            sender.sendData(bluetoothRepo.getSocket(), viewModel);
        } catch (IOException e) {
            Toast.makeText(activity, "Something went wrong while trying to send the data.", Toast.LENGTH_SHORT).show();
            bluetoothRepo.startThreadAndConnect(true);
        }
    }

    /**
     * This will try to connect to the arduino again
     */
    public void tryAgain() {
        bluetoothRepo.startThreadAndConnect(false);
    }
}
