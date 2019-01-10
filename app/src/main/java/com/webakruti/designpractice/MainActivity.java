package com.webakruti.designpractice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGoToPopupPage = (Button)findViewById(R.id.buttonGoToPopupPage);
        buttonGoToPopupPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(MainActivity.this, "Go to Popup!!", Toast.LENGTH_LONG).show();

               /* new AlertDialog.Builder(MainActivity.this)
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
                finish();
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

    }
}
