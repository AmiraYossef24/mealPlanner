package com.example.mealplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import login.view.SignInActivity;
import signup.view.SignUpActivity;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_DELAY=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }
}