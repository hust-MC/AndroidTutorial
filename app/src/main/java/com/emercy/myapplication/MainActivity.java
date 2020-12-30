
package com.emercy.myapplication;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.Random;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

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