package com.pacosanchez.emergencysystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

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

        if (StatConnection.connection != null) {

            Thread thread = new Thread(new Runnable(){
                public void run() {

                    Log.e("Starting thread", "Threeeead");
                    while (true) {

                        if (StatConnection.connection.isEmergency()) {
                            //Emergency
                        }
                    }
                }
            });

            thread.start();
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (connection != null)
            connection.closeConnection();
    }

    public IPConnection getConnection() {
        return connection;
    }
}
