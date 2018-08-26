package com.pacosanchez.emergencysystem;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;



public class fragment_devices extends Fragment implements View.OnClickListener {

    Intent addRaspIntent;
    ImageButton bAddDevice;
    Button addPulseraB;

    ArrayList<Device> deviceList = new ArrayList<>();


    public static fragment_devices newInstance(){
        fragment_devices fragment = new fragment_devices();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addRaspIntent = new Intent(getActivity(),addDevice.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_devices, container, false);

        bAddDevice = v.findViewById(R.id.bAddDevice);
        addPulseraB = v.findViewById(R.id.addPulsera);
        bAddDevice.setOnClickListener(this);
        addPulseraB.setOnClickListener(this);

        deviceList.add(new Device("Brain","Raspberry Pi","Online","10.12.222.146"));


        LinearLayout deviceDisplay = v.findViewById(R.id.idDeviceDisplay);

        if(deviceList.size()>0) {
            for (int i = 0; i < deviceList.size(); i++) {
                TextView textView = new TextView(getActivity());
                textView.setText(deviceList.get(i).getsType() + ": " + deviceList.get(i).getsName());
                textView.setTextSize(18);
                textView.setHeight(100);
                deviceDisplay.addView(textView);

            }
        }

        return v;

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){

            case R.id.bAddDevice:
                startActivity(addRaspIntent);
                break;
            case R.id.addPulsera:

                break;
        }
    }


}
