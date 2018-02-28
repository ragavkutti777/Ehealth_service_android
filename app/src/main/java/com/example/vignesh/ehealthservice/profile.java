package com.example.vignesh.ehealthservice;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class profile extends DialogFragment {

    FirebaseAuth auth;

    DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_profile, container, false);

        final TextView textView,textView1,textView2,textView3,textView4;

        textView=v.findViewById(R.id.textView7);
        textView1=v.findViewById(R.id.textView9);
        textView2=v.findViewById(R.id.textView11);
        textView3=v.findViewById(R.id.textView13);

        Button button=v.findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(),"Logging out "+auth.getCurrentUser().getEmail(),Toast.LENGTH_SHORT).show();
                auth.signOut();
                startActivity(new Intent(getActivity().getApplicationContext(),Login.class));
            }
        });

        auth=FirebaseAuth.getInstance();


        FirebaseUser firebaseUser=auth.getCurrentUser();

        databaseReference= FirebaseDatabase.getInstance().getReference();

        textView.setText(firebaseUser.getEmail());

        databaseReference.child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textView1.setText(dataSnapshot.child("name").getValue().toString());
                textView2.setText(dataSnapshot.child("phone").getValue().toString());
                textView3.setText(dataSnapshot.child("age").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return  v;
    }

}
