package com.stef.arduino.ledcontrolv2.bluetooth;

import android.arch.lifecycle.ViewModelProviders;
import android.bluetooth.BluetoothSocket;
import android.support.v4.app.FragmentActivity;

import com.stef.arduino.ledcontrolv2.abstract_classes.BluetoothDataViewModel;
import com.stef.arduino.ledcontrolv2.viewmodels.GeneralOptionsViewModel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

public class BluetoothSender {
    private GeneralOptionsViewModel generalOptionsViewModel;

    public BluetoothSender(FragmentActivity activity) {
        this.generalOptionsViewModel = ViewModelProviders.of(activity).get(GeneralOptionsViewModel.class);
    }

    /**
     * This will send the data from the generalOptionsViewModel and the Specific data viewmodel over
     * a given bluetoothsocket
     * @param socket the socket to send the data over
     * @param dataViewModel the specific dataviewModel to send the data over
     * @throws SocketException whenever the given socket is invalid, this is thrown.
     */
    public void sendData(BluetoothSocket socket, BluetoothDataViewModel dataViewModel) throws IOException {
        if(socket == null || ! socket.isConnected()) throw new SocketException("This socket is invalid.");
        else {
            Byte[] generalOptionsStream = generalOptionsViewModel.getDataBytes();
            Byte[] specificOptionsStream = dataViewModel.getDataBytes();

            OutputStream ostream = socket.getOutputStream();

            // First write all the generalOptions
            for (Byte data : generalOptionsStream) ostream.write(data);
            // Then write all specific options
            for (Byte data : specificOptionsStream) ostream.write(data);
        }
    }
}
