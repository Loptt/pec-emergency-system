package com.pacosanchez.emergencysystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;


public class fragment_devices extends Fragment implements View.OnClickListener {

    Intent addRaspIntent;
    ImageButton bAddDevice;
    Button addPulseraB;

    IPConnection connection;

    public static fragment_devices newInstance(){
        fragment_devices fragment = new fragment_devices();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addRaspIntent = new Intent(getActivity(),addDevice.class);

        connection = ((MainActivity) (this.getActivity())).getConnection();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_devices, container, false);
        bAddDevice = v.findViewById(R.id.bAddDevice);
        addPulseraB = v.findViewById(R.id.addPulsera);
        bAddDevice.setOnClickListener(this);
        addPulseraB.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){

            case R.id.bAddDevice:
                startActivity(addRaspIntent);
                break;
            case R.id.addPulsera:

                if (StatConnection.connection != null) {

                    Thread thread = new Thread(new Runnable(){
                        public void run() {
                            try {
                                StatConnection.connection.sendOutput("BLT-12345-CONNECT");
                                Toast.makeText(getContext(),"Conectando pulsera...", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    thread.start();
                }
                else {
                    Toast.makeText(getContext(),"Primero conecta el servidor Raspberry", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


}
