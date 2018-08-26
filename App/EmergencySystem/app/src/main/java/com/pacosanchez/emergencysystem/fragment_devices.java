package com.pacosanchez.emergencysystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.ImageButton;


public class fragment_devices extends Fragment implements View.OnClickListener {

    Intent addRaspIntent;
    ImageButton bAddDevice;

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
        bAddDevice.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View v){
        startActivity(addRaspIntent);
    }


}
