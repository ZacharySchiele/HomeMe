package com.example.homeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class bluetoothScanActivity extends AppCompatActivity {

    private Button startScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_scan);

        startScan = (Button)findViewById(R.id.StartScan);
        startScan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getBaseContext(),scanBleDeviceActivity.class);
                startActivity(newIntent);
            }
        });
    }
}
