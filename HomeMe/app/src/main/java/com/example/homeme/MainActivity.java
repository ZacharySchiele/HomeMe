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

    private Button SystemSetUp;
    private Button Routines;
    private Button OperationTime;
    private Button ScanBleDevices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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