package com.example.howmanydaysmanagerver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // toast で受け取りを確認
        Toast.makeText(context, "Received ", Toast.LENGTH_LONG).show();
    }
}