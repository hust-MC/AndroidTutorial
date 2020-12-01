
package com.emercy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends Activity {

    Button save, load;
    TextView content;
    EditText input;

    String data;
    private String file = "MC_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.save);
        load = findViewById(R.id.load);

        input = findViewById(R.id.editText);
        content = findViewById(R.id.content);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                data = input.getText().toString();
                try {
                    FileOutputStream fOut = openFileOutput(file, MODE_WORLD_READABLE);
                    fOut.write(data.getBytes(StandardCharsets.UTF_8));
                    fOut.close();
                    Toast.makeText(getBaseContext(), "文件保存成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        load.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fin = openFileInput(file);
                    byte[] buf = new byte[1024];
                    int count;
                    StringBuilder temp = new StringBuilder();
                    while ((count = fin.read(buf)) > 0) {
                        temp.append(new String(buf, 0, count, StandardCharsets.UTF_8));
                    }
                    content.setText(temp.toString());
                    Toast.makeText(getBaseContext(), "文件加载成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}