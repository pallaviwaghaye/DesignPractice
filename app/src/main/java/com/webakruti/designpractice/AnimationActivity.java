package com.webakruti.designpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class AnimationActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    RatingBar ratingbar;
    Button buttonRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimationActivity.this,FloatingActionButtonActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ratingbar = (RatingBar)findViewById(R.id.ratingBar);
        buttonRating = (Button)findViewById(R.id.buttonRating);

        Rating();

    }

    public void Rating()
    {
        buttonRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating=String.valueOf(ratingbar.getRating());
                String ratingstar=String.valueOf(ratingbar.getNumStars());
                String ratingstep=String.valueOf((int) ratingbar.getStepSize());
                Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),ratingstar , Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),ratingstep , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clockwise(View view) {
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        image.startAnimation(animation);
    }

    public void zoom(View view) {
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.clockwise);
        image.startAnimation(animation1);
    }

    public void fade(View view) {
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fade);
        image.startAnimation(animation1);
    }

    public void blink(View view) {
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.blink);
        image.startAnimation(animation1);
    }

    public void move(View view) {
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        image.startAnimation(animation1);
    }

    public void slide(View view) {
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        image.startAnimation(animation1);
    }

}
