package com.webakruti.designpractice;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WiFiActivity extends AppCompatActivity {
    Button buttonWifiOn,buttonWifiOff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi);

        buttonWifiOn=(Button)findViewById(R.id.buttonWifiOn);
        buttonWifiOff=(Button)findViewById(R.id.buttonWifiOff);

        buttonWifiOn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(true);
            }
        });

        buttonWifiOff.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(false);
            }
        });
    }
}
