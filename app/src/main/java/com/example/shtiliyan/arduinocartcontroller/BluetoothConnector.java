package com.example.shtiliyan.arduinocartcontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by mitko on 6/26/2014.
 */
public class BluetoothConnector {

    BluetoothAdapter mBluetoothAdapter;
    //Съхранява списъка с пернати устройства
    public static HashMap<String, String> mPairedDevices=new HashMap<String,String>();

    //потоци
    protected BluetoothDevice Device;
    protected BluetoothSocket Socket;
    OutputStream OutputStream = null;
    InputStream InputStream = null;
    private BluetoothDevice CurrentDevice;

    //Стартира Bluetooth модулът на устройството ако случайно не е пуснат.
    //Връща true или false в зависимост дали е успяло
    public boolean enableBluetooth() {

        try {
            if (mBluetoothAdapter == null) {
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            }

            if (!mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.enable();

            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnabled(){
        return mBluetoothAdapter.isEnabled();
    }

    //Спира Bluetooth модулът на устройството ако случайно е
    //пуснат.
    //Връща true или false в зависимост дали е успяло
    public boolean disableBluetooth() {
        try {
            if (mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.disable();

            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //Намира всички пернати устройства Записва ги в локален списък за класа
    public void findPairedDivices()
    {//Записва всички пернати устройства адаптера
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HashMap<String,String> map;
        mPairedDevices=new HashMap<String,String>();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevices.put(device.getName() ,device.getAddress());
            }
        }
    }
    //Нaмира всички пернати устройства и се свързва към устройството с
    // име deviceName, ако не успее връща съобщение за грешка което после
    // показваме на потребителя като балонче(Intent)
    /*
    public String FindPairedTargetAndConnect(String deviceName)
    {
        String DeviceID;
        if(mPairedDevices.containsKey(deviceName))
        {
            DeviceID= mPairedDevices.get(deviceName);
            if(ConnectToDevice(DeviceID))
            {

                return "Успешно свързване с устройство: "+deviceName;

            }
            else
            {

                return "Неуспех при свързване с устройство "+deviceName;

            }

        }
        else
        {

            return "Не е открито устройсто с име: "+deviceName;
        }

    }*/

    //отваря конекция към точно упределено устройство
    public boolean openConnection(String Device)
    {
        String address = mPairedDevices.get(Device);
        CurrentDevice = mBluetoothAdapter.getRemoteDevice(address.toUpperCase());
        try {//Тука се генерира UUID(ид-то е някакво от интернет намерено)
            Socket=CurrentDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Socket.connect();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            OutputStream = Socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }

        try {
            InputStream=Socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    //Вътрешен метод за отваряне на конекция. Ползва се от
    // openConnection() и FindPairedTargetAndConnect()
    /*
    private boolean ConnectToDevice(String deviceID) {
        return  openConnection(deviceID);
    }
    */

    public boolean isConnected()
    {
        return Socket.isConnected();
    }
}