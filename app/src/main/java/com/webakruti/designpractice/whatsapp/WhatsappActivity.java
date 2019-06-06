package com.webakruti.designpractice.whatsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.webakruti.designpractice.R;

public class WhatsappActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);

        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
        startActivity(launchIntent);
    }
}
