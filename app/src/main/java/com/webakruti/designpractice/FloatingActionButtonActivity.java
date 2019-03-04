package com.webakruti.designpractice;

import android.content.Intent;
import android.graphics.Color;
//import android.support.design.widget.FloatingActionButton;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

public class FloatingActionButtonActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageViewBack;
    android.support.design.widget.FloatingActionButton fab_plus,fab_facebook,fab_setting,fab_twitter,fab_Gif;
    Animation fabOpen,fabClose,fabRclockwise,fabRanticlcokwise;
    boolean isOpen = false;


    private FloatingActionMenu fab_menu;
    private com.github.clans.fab.FloatingActionButton fabEdit, fabDelete, fabAdd;

    private FloatingActionMenu fab_menu1;
    private com.github.clans.fab.FloatingActionButton fab11, fab12, fab13;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);


        fabAdd = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabAdd);
        fabDelete = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabDelete);
        fabEdit = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabEdit);
        fab_menu = (FloatingActionMenu) findViewById(R.id.fab_menu);

        fab11 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab11);
        fab12 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab12);
        fab13 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab13);
        fab_menu1 = (FloatingActionMenu) findViewById(R.id.fab_menu1);

        fab_plus = (android.support.design.widget.FloatingActionButton)findViewById(R.id.fab_plus);
        fab_facebook = (android.support.design.widget.FloatingActionButton)findViewById(R.id.fab_facebook);
        fab_setting = (android.support.design.widget.FloatingActionButton)findViewById(R.id.fab_setting);
        fab_twitter = (android.support.design.widget.FloatingActionButton)findViewById(R.id.fab_twitter);
        fab_Gif = (android.support.design.widget.FloatingActionButton)findViewById(R.id.fab_Gif);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FloatingActionButtonActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        fabRclockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        fabRanticlcokwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);

        //fab custom menu onclick...........
        fab_plus.setOnClickListener(this);
        fab_Gif.setOnClickListener(this);
        fab_facebook.setOnClickListener(this);
        fab_setting.setOnClickListener(this);
        fab_twitter.setOnClickListener(this);

        //fabmenu onclick..............
        fabDelete.setOnClickListener(this);
        fabEdit.setOnClickListener(this);
        fabAdd.setOnClickListener(this);
        fab_menu.setOnClickListener(this);

        //fab menu2 onclick...............
        fab12.setOnClickListener(this);
        fab11.setOnClickListener(this);
        fab13.setOnClickListener(this);

        fab_menu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    showToast("Menu is opened");
                } else {
                    showToast("Menu is closed");
                }
            }
        });

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.fab_Gif:
                Intent intentGif = new Intent(FloatingActionButtonActivity.this,GIFActivity.class);
                startActivity(intentGif);
                finish();
                break;

            case R.id.fab_facebook:
                Intent intentfb = new Intent(FloatingActionButtonActivity.this,FacebookLoginActivity.class);
                startActivity(intentfb);
                finish();

                /*Snackbar.make(v, "FloatingActionButton is clicked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

               // Toast toast = Toast.makeText(FloatingActionButtonActivity.this, Html.fromHtml("<font color='#e3f2fd' ><b>" + "hello" + "</b></font>"), Toast.LENGTH_LONG);
                Toast toast = Toast.makeText(FloatingActionButtonActivity.this, "Please Give Feedback...", Toast.LENGTH_LONG);
                View view = toast.getView();
                TextView text = (TextView) view.findViewById(android.R.id.message);
                text.setTextColor(Color.YELLOW);
                toast.show();*/

                //toast.setGravity(Gravity.TOP, 0, 0);
                //view.setBackgroundColor(Color.YELLOW);


                //Shadow of the Of the Text Color
                // text.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
                //text.setTextColor(getResources().getColor(R.color.yellow));

                //text.setTextSize(Integer.valueOf(getResources().getString(R.string.text_size)));

                break;

            case R.id.fab_setting:
                Snackbar snackbar = Snackbar
                        .make(v, "Message is deleted", Snackbar.LENGTH_LONG)
                        .setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(view, "Message is restored!", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                                Intent intent = new Intent(FloatingActionButtonActivity.this,EditProfileActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                snackbar.setActionTextColor(Color.RED);

                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();

                break;

            case R.id.fab_twitter:
                Intent intentTwit = new Intent(FloatingActionButtonActivity.this, TwitterActivity.class);
                startActivity(intentTwit);
                finish();

                break;

            case R.id.fab_plus:
                if(isOpen)
                {
                    fab_facebook.startAnimation(fabClose);
                    //  fab_email.setVisibility(View.GONE);
                    fab_setting.startAnimation(fabClose);
                    //  fab_setting.setVisibility(View.GONE);
                    fab_twitter.startAnimation(fabClose);
                    fab_Gif.startAnimation(fabClose);
                    fab_plus.startAnimation(fabRanticlcokwise);
                    fab_facebook.setClickable(false);
                    fab_setting.setClickable(false);
                    fab_twitter.setClickable(false);
                    fab_Gif.setClickable(false);
                    isOpen = false;
                }
                else{

                    fab_facebook.startAnimation(fabOpen);
                    //  fab_email.setVisibility(View.VISIBLE);
                    fab_setting.startAnimation(fabOpen);
                    //  fab_setting.setVisibility(View.VISIBLE);

                    fab_twitter.startAnimation(fabOpen);
                    fab_Gif.startAnimation(fabOpen);
                    fab_plus.startAnimation(fabRclockwise);
                    fab_facebook.setClickable(true);
                    fab_setting.setClickable(true);
                    fab_twitter.setClickable(true);
                    fab_Gif.setClickable(true);
                    isOpen = true;

                }

                break;

            case R.id.fabAdd:
                Snackbar snackbar1 = Snackbar
                        .make(v, "Message is deleted", Snackbar.LENGTH_LONG)
                        .setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(view, "Message is restored!", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                                Intent intent = new Intent(FloatingActionButtonActivity.this,AnimationActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                snackbar1.setActionTextColor(Color.RED);

                View sbView1 = snackbar1.getView();
                TextView textView1 = (TextView) sbView1.findViewById(android.support.design.R.id.snackbar_text);
                textView1.setTextColor(Color.YELLOW);

                snackbar1.show();
                break;

            case R.id.fabEdit:
                Intent intent = new Intent(FloatingActionButtonActivity.this,Clock_TimePickerActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.fabDelete:
                Intent intent1 = new Intent(FloatingActionButtonActivity.this,MainScannerActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.fab_menu:
                if (fab_menu.isOpened()) {
                    fab_menu.close(true);
                }
                break;

            case R.id.fab12:
                Snackbar snackbar2 = Snackbar
                        .make(v, "Message is deleted", Snackbar.LENGTH_LONG)
                        .setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(view, "Message is restored!", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                                Intent intent = new Intent(FloatingActionButtonActivity.this,CustomPopupActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                snackbar2.setActionTextColor(Color.RED);

                View sbView2 = snackbar2.getView();
                TextView textView2 = (TextView) sbView2.findViewById(android.support.design.R.id.snackbar_text);
                textView2.setTextColor(Color.YELLOW);

                snackbar2.show();
                break;

            case R.id.fab11:
                Intent intent2 = new Intent(FloatingActionButtonActivity.this,ZbarMainActivity.class);
                startActivity(intent2);
                finish();
            case R.id.fab13:
                Intent intent3 = new Intent(FloatingActionButtonActivity.this,TabsActivity.class);
                startActivity(intent3);
                finish();

        }

    }
}
