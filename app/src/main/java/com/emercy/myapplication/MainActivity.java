
package com.emercy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread() {
            @Override
            public void run() {
                Log.d("ThreadTest", "Thread1 start");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("ThreadTest", "Thread1 end");

            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("ThreadTest", "Thread2 start");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("ThreadTest", "Thread2 end");
            }
        }).start();
    }

    private void task() {
        for (int i = 0; i < 10; i++) {
            Log.d("Thread", Thread.currentThread().getName() + " 当前i = " + i);
        }
    }
}