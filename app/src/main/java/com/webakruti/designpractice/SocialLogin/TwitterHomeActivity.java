package com.webakruti.designpractice.SocialLogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.webakruti.designpractice.R;

public class TwitterHomeActivity extends AppCompatActivity {
    private TextView textViewUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_home);

        String username= getIntent().getStringExtra("username");

        textViewUsername = (TextView)findViewById(R.id.textViewUsername);
        textViewUsername.setText(username);
    }
}
