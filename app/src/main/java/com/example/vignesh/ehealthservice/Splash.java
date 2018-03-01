package com.example.vignesh.ehealthservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Splash extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView textView=findViewById(R.id.textView3);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_screen_animation);
        textView.startAnimation(animation);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    Thread.sleep(3000);
                    startActivity(new Intent(getApplicationContext(),Login.class));
                    finish();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
