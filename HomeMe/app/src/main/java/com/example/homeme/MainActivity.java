package com.example.homeme;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button SystemSetUp;
    private Button Routines;
    private Button OperationTime;
    private Button BluetoothScan;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SystemSetUp = (Button)findViewById(R.id.SSU);
        Routines = (Button)findViewById(R.id.Routines);
        OperationTime = (Button)findViewById(R.id.OperationTime);
        BluetoothScan = (Button)findViewById(R.id.ScanBleDevices);

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

        BluetoothScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFour = new Intent(getBaseContext(), bluetoothScanActivity.class);
                startActivity(intentFour);
            }
        });
    }
}