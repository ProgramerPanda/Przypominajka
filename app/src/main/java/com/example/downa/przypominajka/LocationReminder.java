package com.example.downa.przypominajka;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LocationReminder extends AppCompatActivity {

    public ImageView panda;

    public TextView mTextView;

    private CallbackManager mCallbackManager;
    public void displayWelcomeMessage(Profile profile){
        if (profile!= null){
            mTextView.setText("Właśnie nazwałeś pandę: " + profile.getName());
        } else mTextView.setText("Bezimienna panda");
    }

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accesToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            displayWelcomeMessage(profile);


        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        mCallbackManager=CallbackManager.Factory.create();
        setContentView(R.layout.activity_location_reminder);

        panda=(ImageView) findViewById(R.id.imageView);
        mTextView=(TextView) findViewById(R.id.textView);

        LoginButton loginButton = (LoginButton) this.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(mCallbackManager,mCallback);

        panda.setImageResource(R.drawable.panda);

    }
    @Override
    public void onResume(){
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayWelcomeMessage(profile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
