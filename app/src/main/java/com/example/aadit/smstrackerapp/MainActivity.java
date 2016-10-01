package com.example.aadit.smstrackerapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button launch;
    static TextView text1;
    static TextView text2;




    //Static TextView t1 = (TextView)findViewById(R.id.Sent);





    protected void print(String sBody, String rBody)
    {
        //Static TextView t1 = (TextView)findViewById(R.id.Sent);
        //t1.setText(sBody);

        //TextView t2 = (TextView)findViewById(R.id.Received);
        //t2.setText(rBody);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.launch = (Button)this.findViewById(R.id.button);
        text1 = (TextView) findViewById(R.id.Sent);
        text2 = (TextView) findViewById(R.id.Received);


        this.launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentFilter filter = new IntentFilter("com.example.aadit.smstrackerapp");
                SMSTrackerActivity myReceiver = new SMSTrackerActivity();
                registerReceiver(myReceiver, filter);

                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.setAction("com.example.aadit.smstrackerapp");
                //intent.putExtra("Foo", "Bar");
                sendBroadcast(intent);


            }
        });



    }










}
