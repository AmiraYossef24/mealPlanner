package com.example.mealplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import login.view.SignInActivity;
import signup.view.SignUpActivity;

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_DELAY=3000;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(firebaseUser==null){
                    Intent intent=new Intent(getApplicationContext(), SignUpActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent=new Intent(getApplicationContext(), AppAcitivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_DELAY);


    }
}