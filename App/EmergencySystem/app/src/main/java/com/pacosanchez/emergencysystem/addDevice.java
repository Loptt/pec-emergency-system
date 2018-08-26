package com.pacosanchez.emergencysystem;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class addDevice extends AppCompatActivity {


    Button addDevice;

    EditText raspName;
    EditText raspIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        raspName = findViewById(R.id.raspName_et);
        raspIp = findViewById(R.id.raspIp_et);

        addDevice = findViewById(R.id.bAddRasp);

    }
}
