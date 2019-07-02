package com.stef.arduino.ledcontrolv2.bluetooth;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.ParcelUuid;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class Bluetooth {
    private static final String MAC_ADDRESS = "98:D3:32:21:5C:41";
    private final BluetoothAdapter mAdapter;
    private static Bluetooth instance;
    private Activity activity;
    private BluetoothSocket socket;
    private MutableLiveData<Boolean> isConnected;
    private MutableLiveData<String> hasError;

    private Bluetooth(Activity activity) {
        this.activity = activity;
        this.hasError = new MutableLiveData<>();
        this.isConnected = new MutableLiveData<>();
        this.isConnected.postValue(false);
        this.hasError.postValue(null);
        mAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    // Singleton class
    public static Bluetooth getInstance(Activity activity) {
        if(instance == null) instance = new Bluetooth(activity);

        return instance;
    }

    public LiveData<Boolean> getIsConnected() {
        return isConnected;
    }
    public LiveData<String> getHasError() { return hasError; }

    /**
     * This will detect when the state has changed for the adapter and will then execute the discover device
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            discoverDevices();
        }
    };

    /**
     * This will start a new thread and attempt to connect to the arduino
     */
    public void startThreadAndConnect() {
        new Thread() {
            @Override public void run() {
                checkBluetoothOn();
            }
        }.start();
    }

    /**
     * Method to check whether the devices bluetooth is currently on. This will also try to detect
     * which bluetooth devices can be connected to.
     */
    private void checkBluetoothOn() {
        // Set the value to false just until a connection is confirmed
        isConnected.postValue(false);
        hasError.postValue(null);

        // Check if bluetooth is available and if it is enabled
        if (mAdapter != null) {
            if (!mAdapter.isEnabled()) {
                Intent turnBon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                activity.startActivity(turnBon);

                // this will make sure the devices only get discovered when the adapter is actually on
                IntentFilter stateChangedFilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
                activity.registerReceiver(mReceiver, stateChangedFilter);
            } else {
                discoverDevices();
            }
        } else {
            // The bluetooth device isn't available
            Toast.makeText(activity, "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();
        }
    }

    public void unregister() {
        // Try this for whenever the activity was never registered
        try {
            activity.unregisterReceiver(mReceiver);
        } catch (Exception e) {}
    }

    /**
     * Method to discover the devices
     */
    private void discoverDevices(){
        Set<BluetoothDevice> bondedDevices = mAdapter.getBondedDevices();

        boolean foundDevice = false;

        if(bondedDevices.size() > 0){
            for (BluetoothDevice bondedDevice : bondedDevices) {
                // Try to instantly connect to the bluetooth module connected to the arduino
                if(bondedDevice.getAddress().equals(MAC_ADDRESS)) {
                    foundDevice = true;
                    // Device found, connect to it.
                    this.socket = getConnectedSocket(bondedDevice);
                }
            }

            if(! foundDevice) {
                hasError.postValue("The arduino can not be found.");
            }
        } else {
            hasError.postValue("No devices found to connect to.");
        }
    }

    /**
     * Actually connect to the device
     * @param device the device to connect to
     * @return the socket whenever the device has been connected
     */
    private BluetoothSocket getConnectedSocket(BluetoothDevice device) {
        ParcelUuid[] uuids = device.getUuids();

        BluetoothSocket newSocket = socket;

        // Only try to connect when the device isn't already connected
        if(newSocket == null || !newSocket.isConnected()) {
            try {
                newSocket = device.createRfcommSocketToServiceRecord(uuids[0].getUuid());

                // try to connect to the socket
                try {
                    newSocket.connect();
                } catch (IOException e) {
                    // This will attempt the fallback
                    try {
                        newSocket = (BluetoothSocket)device.getClass().getMethod("createRfcommSocket", int.class).invoke(device,1);
                        newSocket.connect();
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e2) {
                        hasError.postValue("Connecting to the selected device has failed.");
                        return null;
                    }
                }
            } catch (IOException e) {
                hasError.postValue("Something went wrong trying to create the bluetooth socket.");
                return null;
            }
        }

        isConnected.postValue(true);
        hasError.postValue(null);

        return newSocket;
    }

    public BluetoothSocket getSocket() {
        return socket;
    }
}
