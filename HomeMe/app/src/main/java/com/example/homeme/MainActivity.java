package com.example.homeme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Vector;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    private Button SystemSetUp;
    private Button Routines;
    private Button OperationTime;
    private Button ScanBleDevices;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use this check to determine whether BLE is supported on the device. Then
        // you can selectively disable BLE-related features.
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            finish();
        }
        // Initializes Bluetooth adapter.
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        // Ensures Bluetooth is available on the device and it is enabled. If not,
        // displays a dialog requesting user permission to enable Bluetooth.
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }


        SystemSetUp = (Button)findViewById(R.id.SSU);
        Routines = (Button)findViewById(R.id.Routines);
        OperationTime = (Button)findViewById(R.id.OperationTime);
        ScanBleDevices = (Button)findViewById(R.id.ScanBleDevices);

        SystemSetUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOne = new Intent(getBaseContext(), systemSetUpActivity.class);
                startActivity(intentOne);
            }
        });

        Routines.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentTwo = new Intent(getBaseContext(), storeRoutineActivity.class);
                startActivity(intentTwo);
            }
        });

        OperationTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentThree = new Intent(getBaseContext(), operationTimeActivity.class);
                startActivity(intentThree);
            }
        });

        ScanBleDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFour = new Intent(getBaseContext(), scanBleDeviceActivity.class);
                startActivity(intentFour);
            }
        });
    }
}