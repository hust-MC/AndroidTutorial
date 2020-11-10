package com.emercy.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class EventHandle implements View.OnClickListener {

    Context mContext;

    public EventHandle(Context context) {
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        // 点击回调中处理事件
        Toast.makeText(mContext.getApplicationContext(), "Button被点击了", Toast.LENGTH_LONG).show();
    }
}
