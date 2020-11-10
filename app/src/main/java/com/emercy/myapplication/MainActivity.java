
package com.emercy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final int MAX = 100;
    private static final int START_PROGRESS = 100;
    private static final int UPDATE_COUNT = 200;

    private ProgressBar progressBar;
    private Button startProgress;
    private TextView textView;
    private boolean mHasStart;

    // 任务2：在主线程刷新进度条
    Handler mHandlerThread = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == START_PROGRESS) {
                if (!mHasStart) {
                    thread.start();
                    mHasStart = true;
                }
            } else if (msg.what == UPDATE_COUNT) {
                textView.setText("当前进度：" + msg.arg1 + "%");
                progressBar.setProgress(msg.arg1);
            }
        }
    };

    // 任务1：在子线程执行耗时操作，通过sleep模拟耗时任务
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i <= 100; i++) {
                try {
                    // 一秒钟的耗时操作
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                message.what = UPDATE_COUNT;
                message.arg1 = i;
                mHandlerThread.sendMessage(message);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        startProgress = findViewById(R.id.start_progress);
        textView = findViewById(R.id.textView);
        progressBar.setMax(MAX);

        startProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 任务3：通过Handler传递进度消息
                Message message = new Message();
                message.what = START_PROGRESS;
                mHandlerThread.sendEmptyMessage(START_PROGRESS);
            }
        });
    }
}
