package com.pacosanchez.emergencysystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    IPConnection connection;
    Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);

        SharedPreferences preferences = this.getPreferences(Context.MODE_PRIVATE);
        final String ipAddress = preferences.getString("address","0.0.0.0");

        if (!ipAddress.equals("0.0.0.0")) {

            Thread thread = new Thread(new Runnable(){
                public void run() {
                    try {
                        socket = new Socket(ipAddress, 7500);
                        connection = new IPConnection(socket);
                        connection.start();

                        //Toast.makeText(addDevice.this, "Dispositivo conectado", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();

        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.navigation_devices:
                        selectedFragment=fragment_devices.newInstance();
                        break;
                    case R.id.navigation_members:
                        selectedFragment=fragment_members.newInstance();
                        break;
                    case R.id.navigation_emergency:
                        selectedFragment=fragment_emergency.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container,selectedFragment);
                transaction.commit();
                return true;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,fragment_devices.newInstance());
        transaction.commit();
    }
}
