
package com.emercy.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final int MAX = 100;

    private ProgressBar progressBar;
    private Button startProgress;
    private TextView textView;

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
                new DownloadTask().execute();
            }
        });
    }

    // 1、创建Async Task子类
    private class DownloadTask extends AsyncTask<Integer, Integer, String> {

        // 2、初始化阶段，展示进度条
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
        }

        // 3、执行后台任务
        @Override
        protected String doInBackground(Integer... integers) {
            int i;
            for (i = 0; i < 100; i++) {
                try {
                    // 一秒钟的耗时操作
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 4、发布进度
                publishProgress(i);
            }
            return "异步任务已完成";
        }

        // 5、接收后台任务数据并更新进度条
        @Override
        protected void onProgressUpdate(Integer... values) {
            textView.setText("当前进度：" + values[0] + "%");
            progressBar.setProgress(values[0]);
        }

        // 6、任务结束
        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.GONE);
            textView.setText(s);
        }
    }
}
