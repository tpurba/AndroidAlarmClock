package com.example.alarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class BlueToothActivity extends AppCompatActivity {
    private static final String TAG = "BlueToothActivity";
    BluetoothAdapter bluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        // Check Bluetooth Availability
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            String message = "Bluetooth does not work with your device";
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            Log.d(TAG, message);
        } else {
            // Bluetooth is supported
            String message = "Bluetooth is supported";
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            Log.d(TAG, message);
            // You can add additional Bluetooth-related functionality here
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return true;
    }

}