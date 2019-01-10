package com.webakruti.designpractice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class FacebookLoginActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private ImageView imageViewImage;
    private TextView textViewEmail;
    private TextView textViewBirthday;
    private TextView textViewFriends;
    ProgressDialog mDialog;

    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);

        callbackManager = CallbackManager.Factory.create();

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacebookLoginActivity.this,FloatingActionButtonActivity.class);
                startActivity(intent);
                finish();
            }
        });

        textViewBirthday = (TextView)findViewById(R.id.textViewBirthday);
        textViewEmail = (TextView)findViewById(R.id.textViewEmail);
        textViewFriends = (TextView)findViewById(R.id.textViewFriends);

        imageViewImage = (ImageView)findViewById(R.id.imageViewImage);

        //printKeyHash();

        final LoginButton loginButton = (LoginButton)findViewById(R.id.button_FB_login);
        loginButton.setReadPermissions(Arrays.asList("public_profile","email","user_birthday","user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mDialog = new ProgressDialog(FacebookLoginActivity.this);
                mDialog.setMessage("retrieving data...");
                mDialog.cancel();

                String accesstoken = loginResult.getAccessToken().getToken();

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        mDialog.dismiss();

                        Log.d("response:",response.toString());
                        getData(object);
                    }
                });

                //request graph API
                Bundle parameters = new Bundle();
                parameters.putString("fields","id,email,birthday,friends");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        //if already login

        if(AccessToken.getCurrentAccessToken() != null)
        {
            //just set userid
            textViewEmail.setText(AccessToken.getCurrentAccessToken().getUserId());
        }

    }

    private void getData(JSONObject object) {
        try {
            URL profile_picture = new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=250&height=250");

            Picasso.with(this).load(profile_picture.toString()).into(imageViewImage);

            textViewEmail.setText(object.getString("email"));
            textViewBirthday.setText(object.getString("birthday"));
            textViewFriends.setText("Friends: "+object.getJSONObject("friends").getJSONObject("summary").getString("total_count"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }


    //to get keyhash programatically-----------------------------------------------
    private void printKeyHash() {
        try{
            PackageInfo info = getPackageManager().getPackageInfo("com.webakruti.designpractice", PackageManager.GET_SIGNATURES);
            for(Signature signature:info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //to get keyhash programatically-----------------------------------------------
}
