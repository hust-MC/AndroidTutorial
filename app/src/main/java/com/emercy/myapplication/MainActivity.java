
package com.emercy.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText userName, pwd;
    Button loginBtn;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.et_account);
        pwd = findViewById(R.id.et_password);
        loginBtn = findViewById(R.id.login);
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        final Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        if (pref.contains("username") && pref.contains("password")) {
            startActivity(intent);
        }
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userName.getText().toString();
                String password = pwd.getText().toString();
                if (username.equals("超低空") && password.equals("慕课网")) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "账号或者密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}