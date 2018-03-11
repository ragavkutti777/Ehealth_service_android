package com.example.vignesh.ehealthservice;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class request_options extends DialogFragment {


    public request_options() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.fragment_request_options, container, false);
        Button button=v.findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Main2Activity)getActivity()).food();
            }
        });
        Button button1=v.findViewById(R.id.button10);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Main2Activity)getActivity()).water();
            }
        });
        Button button2=v.findViewById(R.id.button11);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Main2Activity)getActivity()).restroom();
            }
        });
        return v;
    }

}
