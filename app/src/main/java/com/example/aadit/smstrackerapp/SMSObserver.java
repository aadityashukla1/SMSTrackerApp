package com.example.aadit.smstrackerapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.ContentObserver;

/**
 * Created by aadit on 4/23/2016.
 */
import android.content.Context;
        import android.database.ContentObserver;
        import android.database.Cursor;
        import android.net.Uri;
        import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;

import java.security.Timestamp;
import java.util.Date;

public class SMSObserver extends ContentObserver {

    private static final String TAG = "SMSTRACKER";
    private static final Uri STATUS_URI = Uri.parse("content://sms");

    private Context mContext;

    public SMSObserver(Handler handler, Context ctx) {
        super(handler);
        mContext = ctx;
    }

    public boolean deliverSelfNotifications() {
        return true;
    }

    public void onChange(boolean selfChange) {

                        try{
                            Log.e(TAG, "Notification on SMS observer");
                            Cursor sms_sent_cursor = mContext.getContentResolver().query(STATUS_URI, null, null, null, null);
                            if (sms_sent_cursor != null) {
                                if (sms_sent_cursor.moveToFirst()) {
                                    int protocol = sms_sent_cursor.getInt(sms_sent_cursor.getColumnIndex("protocol"));
                                    //String protocol = sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("protocol"));
                                    Log.e(TAG, "protocol : " + protocol);
                                    if(protocol == 0){
                                        //String[] colNames = sms_sent_cursor.getColumnNames();
                                        int type = sms_sent_cursor.getInt(sms_sent_cursor.getColumnIndex("type"));
                                        Log.e(TAG, "SMS Type : " + type);
                                        if(type == 2 || type == 1){
                                            int ty = type;
                                            String nameType = "Message sent";
                                            if (ty == 1)
                                            {
                                                nameType = "Message received";
                                            }
                                            else if (ty == 2)
                                            {
                                                nameType = "Message sent";
                                            }

                                            String personName = sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("address"));



                                            long dat = sms_sent_cursor.getLong(sms_sent_cursor.getColumnIndex("date"));
                                            Date d = new Date(dat);


                                            String messageBody = sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("body"));
                                            //MainActivity.text2.setText(messageBody);

                                            MainActivity.text1.setText("Message Type: "+nameType+"\n"+"PhoneNumber: "+personName+"\n"+" Date: "+d.toString()+"\n"+"Message Content: "+messageBody);






                                        //if(type == 2){
                                            Log.e(TAG, "Id : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("_id")));
                                            Log.e(TAG, "Thread Id : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("thread_id")));
                                            Log.e(TAG, "Address : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("address")));
                                            Log.e(TAG, "Person : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("person")));
                                            Log.e(TAG, "Date : " + sms_sent_cursor.getLong(sms_sent_cursor.getColumnIndex("date")));
                                            Log.e(TAG, "Read : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("read")));
                                            Log.e(TAG, "Status : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("status")));
                                            Log.e(TAG, "Type : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("type")));
                                            Log.e(TAG, "Rep Path Present : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("reply_path_present")));
                                            Log.e(TAG, "Subject : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("subject")));
                                            Log.e(TAG, "Body : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("body")));
                                            Log.e(TAG, "Err Code : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("error_code")));
			        		/*
			        		if(colNames != null){
			        			for(int k=0; k<colNames.length; k++){
			        				Log.e(TAG, "colNames["+k+"] : " + colNames[k]);
			        			}
			        		}
			        		*/
                                        }
                                    }
                                }
                            }
                            else
                                Log.e(TAG, "Send Cursor is Empty");
                        }
                        catch(Exception sggh){
                            Log.e(TAG, "Error on onChange : "+sggh.toString());
                        }
                        super.onChange(selfChange);

    }//fn onChange

}//End of class SmsSentObserver