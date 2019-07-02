package com.stef.arduino.ledcontrolv2.ui.activities;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stef.arduino.ledcontrolv2.R;
import com.stef.arduino.ledcontrolv2.bluetooth.Bluetooth;

public class MainActivity extends AppCompatActivity {

    private Bluetooth bluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Try connecting to the bluetooth device
        if(bluetooth == null) bluetooth = Bluetooth.getInstance(this);
        new Thread() {
            @Override public void run() {
                Looper.prepare();
                bluetooth.checkBluetoothOn();
            }
        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(bluetooth == null) bluetooth = Bluetooth.getInstance(this);
        bluetooth.unregister();
    }
}
