package com.webakruti.designpractice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.webakruti.designpractice.Game.GameMainActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerChangeStatus;
    private Spinner spinner1;
    private Spinner spinner2;
    private LinearLayout linearLayoutGotoUserLogin;

    private RelativeLayout relativeLayoutShare;

    private Button buttonGoToPopupPage;
    private Button buttonGotoFloatingButton;

    private Toolbar toolbar;


    //private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigationHome:
                    //mTextMessage.setText("Home");
                    Intent intent4 = new Intent(MainActivity.this,RunningCatAnimationActivity.class);
                    startActivity(intent4);
                    finish();
                    return true;
                case R.id.navigationMyProfile:
                    //mTextMessage.setText("Profile");
                    Intent intent = new Intent(MainActivity.this,EditProfileActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.navSupport:
                   // mTextMessage.setText("Support");
                    Intent intent1 = new Intent(MainActivity.this,ChartActivity.class);
                    startActivity(intent1);
                    finish();
                    return true;
                case R.id.navigationMyEnquiry:
                   // mTextMessage.setText("Enquiry");
                    Intent intent2 = new Intent(MainActivity.this,TableActivity.class);
                    startActivity(intent2);
                    finish();
                    return true;
                case R.id.navigationLogout:
                    //mTextMessage.setText("Logout");
                    Intent intent3 = new Intent(MainActivity.this,LightAnimationActivity.class);
                    startActivity(intent3);
                    finish();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        buttonGoToPopupPage = (Button)findViewById(R.id.buttonGoToPopupPage);
        buttonGoToPopupPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(GameMainActivity.this, "Go to Popup!!", Toast.LENGTH_LONG).show();

               /* new AlertDialog.Builder(GameMainActivity.this)
                        .setMessage("Go to Popup!!")
                        .setPositiveButton("OK", null)
                        .show();*/

                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Go to popup!!")
                        .setPositiveButton("OK",
                        new DialogInterface.OnClickListener()
                        {   public void onClick(DialogInterface di,int id)
                        {
                            Intent intent = new Intent(MainActivity.this,CustomPopupActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        }
                )
                        .create().show();
            }
        });

        buttonGotoFloatingButton = (Button)findViewById(R.id.buttonGotoFloatingButton);
        buttonGotoFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FloatingActionButtonActivity.class);
                startActivity(intent);
                //finish();
                //used to give transition animation from right to left
                overridePendingTransition(R.anim.go_right,R.anim.go_left);
                Toast.makeText(MainActivity.this, "Go to Floating Button page!!", Toast.LENGTH_LONG).show();
            }
        });

        relativeLayoutShare = (RelativeLayout)findViewById(R.id.relativeLayoutShare);
        relativeLayoutShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody = "share";
                String shareSub = "https://dzone.com/articles/how-to-create-badges-item-counts-in-android , Bajaj Nagar, Nagpur";
                intent.putExtra(Intent.EXTRA_SUBJECT,shareBody);
                intent.putExtra(Intent.EXTRA_TEXT,shareSub);
                startActivity(Intent.createChooser(intent,"Share"));

            }
        });

        linearLayoutGotoUserLogin = (LinearLayout)findViewById(R.id.linearLayoutGotoUserLogin);
        linearLayoutGotoUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        spinnerChangeStatus = (Spinner)findViewById(R.id.spinnerChangeStatus);
        String[] list = getResources().getStringArray(R.array.stations);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        spinnerChangeStatus.setAdapter(adapter);

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        String[] list1 = getResources().getStringArray(R.array.stations);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list1);
        spinner1.setAdapter(adapter1);

        //ascending sort for spinner1 of list1

        Collections.sort(Arrays.asList(list1), new Comparator<String>(){
            public int compare(String a1, String a2) {
                String stringName1 = a1;
                String stringName2 = a2;
                return stringName1.compareToIgnoreCase(stringName2);
            }
        });

        //ascending sort for spinner1 of list1

        spinner2 = (Spinner)findViewById(R.id.spinner2);
        String[] list2 = getResources().getStringArray(R.array.stations);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list2);
        spinner2.setAdapter(adapter2);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dotmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.home:
                Intent intent = new Intent(MainActivity.this,NavigationActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.settings:
                Intent intent1 = new Intent(MainActivity.this,EditProfileActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.Wifi:
                Intent intent2 = new Intent(MainActivity.this,WiFiActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.WifiList:
                Intent intent3 = new Intent(MainActivity.this,WiFiListActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.help:
                Intent intent4 = new Intent(MainActivity.this,LocalNotificationActivity.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.game:
                Intent intent5 = new Intent(MainActivity.this,GameMainActivity.class);
                startActivity(intent5);
                finish();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.go_down,R.anim.go_down);
    }
}
