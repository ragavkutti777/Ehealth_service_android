package com.example.vignesh.ehealthservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class choose_host extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_host);

        mAuth=FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),Login.class));
        }
    }

    public void go_online(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    public void go_local(View view) {
        startActivity(new Intent(getApplicationContext(),Main2Activity.class));
        finish();
    }
}
