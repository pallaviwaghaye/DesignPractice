package com.webakruti.designpractice;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LightAnimationActivity extends AppCompatActivity {
    private ImageView imageViewBulb;
    private Button buttonSwitch;
    private ImageView imageViewBack;

    boolean turnOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_animation);

        imageViewBulb = (ImageView)findViewById(R.id.imageViewBulb);
        buttonSwitch = (Button)findViewById(R.id.buttonSwitch);
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LightAnimationActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!turnOn) {
                    imageViewBulb.setImageResource(R.drawable.bulb_trans_on);
                    ((TransitionDrawable) imageViewBulb.getDrawable()).startTransition(3000);
                    turnOn = true;
                }else
                {
                    imageViewBulb.setImageResource(R.drawable.bulb_trans_off);
                    ((TransitionDrawable) imageViewBulb.getDrawable()).startTransition(3000);
                    turnOn = false;
                }
            }
        });
    }
}
