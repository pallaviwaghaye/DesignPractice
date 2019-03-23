package com.webakruti.designpractice;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class WiFiListActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 1;
    private static Button enable, disable, scan;
    private static ListView scannedWifi;

    private static WifiManager wifiManager;// Wifi Manager to manage wifi
    private static WifiReceiver receiverWifi; // Wifi broadcase receiver
    private static List<ScanResult> wifiList;// List to store scanned wifi's
    String wifis[];
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi_list);
        init();
        setListeners();

        scannedWifi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // selected item
                String ssid = ((TextView) view).getText().toString();
                connectToWifi(ssid);
                Toast.makeText(WiFiListActivity.this,"Wifi SSID : "+ssid,Toast.LENGTH_SHORT).show();

            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method

        }else{
            //getScanningResults();
            //do something, permission was previously granted; or legacy device
        }


    }

    private void getScanningResults() {
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();// Start wifi scan
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Do something with granted permission
            //wifiManager.getScanResults();
            wifiManager.startScan();
        }
    }

    private void connectToWifi(final String wifiSSID) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.connect_wifi);
        dialog.setTitle("Connect to Network");
        TextView textSSID = (TextView) dialog.findViewById(R.id.textSSID1);

        Button dialogButton = (Button) dialog.findViewById(R.id.okButton);
        pass = (EditText) dialog.findViewById(R.id.textPassword);
        textSSID.setText(wifiSSID);

        // if button is clicked, connect to the network;
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkPassword = pass.getText().toString();
                finallyConnect(checkPassword, wifiSSID);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void finallyConnect(String networkPass, String networkSSID) {
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", networkSSID);
        wifiConfig.preSharedKey = String.format("\"%s\"", networkPass);

        // remember id
        int netId = wifiManager.addNetwork(wifiConfig);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"\"" + networkSSID + "\"\"";
        conf.preSharedKey = "\"" + networkPass + "\"";
        wifiManager.addNetwork(conf);
    }



    // Initialize all views
    private void init() {
        enable = (Button) findViewById(R.id.enable);
        disable = (Button) findViewById(R.id.disable);
        scan = (Button) findViewById(R.id.scan_wifi);
        scannedWifi = (ListView) findViewById(R.id.show_scaned_wifi);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        // get wifi service to use wifi

        receiverWifi = new WifiReceiver();// Broadcast receiver for wifi

    }

    // Setting click listener over buttons
    private void setListeners() {
        enable.setOnClickListener(this);
        disable.setOnClickListener(this);
        scan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enable:

                // Check if wifi is enabled or disabled and do according to it
                if (!wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(true);// enable wifi
                    Toast.makeText(WiFiListActivity.this, "WIFI enabled.",
                            Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(WiFiListActivity.this, "WIFI already enabled.",
                            Toast.LENGTH_SHORT).show();
                break;

            case R.id.disable:
                // If wifi is enabled then disable it
                if (wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(false);// disable wifi
                    Toast.makeText(WiFiListActivity.this, "WIFI disabled.",
                            Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(WiFiListActivity.this, "WIFI is not enabled.",
                            Toast.LENGTH_SHORT).show();

                break;
            case R.id.scan_wifi:
                // If wifi is enabled then scan wifi's
                if (wifiManager.isWifiEnabled()) {
                    // Register broadcast receiver
                    // Broacast receiver will automatically call when number of wifi
                    // connections changed
				/*registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
				wifiManager.startScan();// Start wifi scan*/

                    getScanningResults();

                } else
                    Toast.makeText(WiFiListActivity.this, "WIFI is not enabled.", Toast.LENGTH_SHORT).show();

                break;
        }

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        // On destroy we have to unregister receiver
        try {
            unregisterReceiver(receiverWifi);
        } catch (Exception e) 
            e.printStackTrace();
        }
    }



    // Broadcast receiver to scan new wifi
    class WifiReceiver extends BroadcastReceiver {

        // This method call when number of wifi connections changed and
        // disaplayed wifi connections over listview
        public void onReceive(Context c, Intent intent) {
            //String action = intent.getAction();// Get sent action

            // If sent action is equal to scan results
            //if (action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {

            wifiList = wifiManager.getScanResults();// get scanned results in list

            //Toast.makeText(MainActivity.this, (CharSequence) wifiManager.getScanResults(),Toast.LENGTH_LONG).show();

            //wifiManager.getScanResults();
            if (wifiList.size() > 0) {// if size is greater then 0 then show
                // wifi connection over listview
                wifis = new String[wifiList.size()];
                for(int i = 0; i < wifiList.size(); i++){
                    wifis[i] = ((wifiList.get(i)).toString());
                }
                String filtered[] = new String[wifiList.size()];
                int counter = 0;
                for (String eachWifi : wifis) {
                    String[] temp = eachWifi.split(",");

                    filtered[counter] = temp[0].substring(5).trim();//+"\n" + temp[2].substring(12).trim()+"\n" +temp[3].substring(6).trim();//0->SSID, 2->Key Management 3-> Strength

                    counter++;

                }
                //scannedWifi.setAdapter(new ArrayAdapter<ScanResult>(getApplicationContext(),R.layout.list_wifi_item,R.id.label, filtered));

                Toast.makeText(WiFiListActivity.this, "Number of Wifi connections found : " + wifiList.size(), Toast.LENGTH_SHORT).show(); // Toast to display no. of connections
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, filtered);
                scannedWifi.setAdapter(adapter);// Setting adapter over
                // listview
                adapter.notifyDataSetChanged();// Notify adapter
            } else {
                // If list size is 0 then show toast
                Toast.makeText(WiFiListActivity.this, "No Wifi found.",
                        Toast.LENGTH_SHORT).show();
            }
            //}

        }
    }



}
