package com.example.vignesh.ehealthservice;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URISyntaxException;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class Main2Activity extends AppCompatActivity {

    TextView textView;

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.1.5:5000/");
        } catch (URISyntaxException e) {e.printStackTrace();}
    }

    String url="http://192.168.1.5:5000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView2);
        mSocket.connect();
        mSocket.on("notify",notification);
        mSocket.on("rk",notification1);
        mSocket.on("alert_m",notification2);
    }

    Emitter.Listener notification=new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jsonObject= (JSONObject) args[0];
                        String message=jsonObject.getString("message");
                        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(Main2Activity.this)
                                .setAutoCancel(false)
                                .setContentTitle("E-health care")
                                .setContentText(message)
                                .setSound(uri)
                                .setSmallIcon(R.mipmap.ic_launcher);
                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(2,builder.build());

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    Emitter.Listener notification1=new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try
                    {
                        JSONObject jsonObject= (JSONObject) args[0];
                        String message=jsonObject.getString("message");
                        textView.setText(new StringBuilder("Heart rate value "+message+"bpm"));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    Emitter.Listener notification2=new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jsonObject= (JSONObject) args[0];
                        String message=jsonObject.getString("message");
                        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(Main2Activity.this)
                                .setAutoCancel(false)
                                .setContentTitle("E-health care")
                                .setContentText(message)
                                .setSound(uri)
                                .setSmallIcon(R.mipmap.ic_launcher);
                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(2,builder.build());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });
        }
    };

    public void alert(View v)
    {
        request_options request_options=new request_options();
        request_options.show(getSupportFragmentManager(),"request options");
    }

    public void water()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, new String(new StringBuilder(url+"water")), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("done"))
                {
                    Toast.makeText(getApplicationContext(),"Alert sent",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Something went wrong.. Try again",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof TimeoutError||error instanceof NoConnectionError)
                {
                    Toast.makeText(getApplicationContext(),"Check your network connectivity",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Something went wrong.. Try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
        RequestQueue requestQueue=newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void food()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, new String(new StringBuilder(url+"food")), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("done"))
                {
                    Toast.makeText(getApplicationContext(),"Alert sent",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Something went wrong.. Try again",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof TimeoutError||error instanceof NoConnectionError)
                {
                    Toast.makeText(getApplicationContext(),"Check your network connectivity",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Something went wrong.. Try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
        RequestQueue requestQueue=newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void restroom()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, new String(new StringBuilder(url+"restroom")), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("done"))
                {
                    Toast.makeText(getApplicationContext(),"Alert sent",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Something went wrong.. Try again",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof TimeoutError||error instanceof NoConnectionError)
                {
                    Toast.makeText(getApplicationContext(),"Check your network connectivity",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Something went wrong.. Try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
        RequestQueue requestQueue=newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void open_profile(View v)
    {
        profile profile=new profile();
        profile.show(getSupportFragmentManager(),"Profile");
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(!mSocket.connected())
        {
            mSocket.connect();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
    }
}
