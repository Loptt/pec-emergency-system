package com.pacosanchez.emergencysystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.net.Socket;
import java.util.ArrayList;

public class addDevice extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String ipAddress, deviceName;

    Button addDevice;

    EditText raspName;
    EditText raspIp;

    Socket socket;

    ArrayList<Device> deviceList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);


        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);


        raspName = findViewById(R.id.raspName_et);
        raspIp = findViewById(R.id.raspIp_et);

        addDevice = findViewById(R.id.bAddRasp);

        addDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ipAddress = raspIp.getText().toString();
                deviceName = raspName.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("address", ipAddress);
                editor.putString("deviceName", deviceName);

                Intent intent = new Intent(addDevice.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
