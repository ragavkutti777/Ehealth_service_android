package com.example.vignesh.ehealthservice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity
{

    EditText editText,editText1,editText2,editText3,editText4,editText5;

    DatabaseReference databaseReference;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editText=findViewById(R.id.editText3);
        editText1=findViewById(R.id.editText4);
        editText2=findViewById(R.id.editText5);
        editText3=findViewById(R.id.editText6);
        editText4=findViewById(R.id.editText7);
        editText5=findViewById(R.id.editText8);

        auth=FirebaseAuth.getInstance();

        databaseReference= FirebaseDatabase.getInstance().getReference();
    }

    public void signup(View view)
    {
        final String name=editText.getText().toString();
        final String email=editText1.getText().toString();
        final String age=editText2.getText().toString();
        String password=editText3.getText().toString();
        String re_password=editText4.getText().toString();
        final String phone=editText5.getText().toString();

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(email)||TextUtils.isEmpty(age)||TextUtils.isEmpty(password)||TextUtils.isEmpty(re_password)||TextUtils.isEmpty(phone))
        {
            Toast.makeText(getApplicationContext(),"Enter your credentials",Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(re_password))
        {
            Toast.makeText(getApplicationContext(),"Password doesn't match",Toast.LENGTH_SHORT).show();
        }
        else
        {
            auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                signup_data_class signup_data_class=new signup_data_class(name,age,phone,email);
                                FirebaseUser firebaseUser=auth.getCurrentUser();
                                databaseReference.child(firebaseUser.getUid()).setValue(signup_data_class);
                                Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Something went wrong... Try again",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();
    }
}
