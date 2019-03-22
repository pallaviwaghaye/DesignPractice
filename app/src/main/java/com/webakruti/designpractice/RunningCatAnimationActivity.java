package com.webakruti.designpractice;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RunningCatAnimationActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button buttonClick;
    private ImageView imageViewBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_cat_animation);

        imageView = (ImageView)findViewById(R.id.imageView);
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        buttonClick = (Button)findViewById(R.id.buttonClick);


        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RunningCatAnimationActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.running_cat);
                AnimationDrawable runningCat = (AnimationDrawable)imageView.getDrawable();
                runningCat.start();
            }
        });
    }
}
