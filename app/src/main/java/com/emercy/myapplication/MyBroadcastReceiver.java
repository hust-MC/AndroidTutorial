package com.emercy.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    static final String BROADCAST_INTENT = "com.emercy.CUSTOM_RECEIVER";

    @Override
    public void onReceive(Context context, Intent intent) {
        CharSequence data = intent.getCharSequenceExtra("msg");
        Toast.makeText(context, "接收到的消息: " + data, Toast.LENGTH_LONG).show();
    }
}