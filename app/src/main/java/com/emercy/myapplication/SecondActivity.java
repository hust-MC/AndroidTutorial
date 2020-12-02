package com.emercy.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {
    SharedPreferences sharedPreferences;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        TextView result = findViewById(R.id.result);
        Button btnLogOut = findViewById(R.id.logout);
        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(SecondActivity.this, MainActivity.class);
        result.setText("欢迎您, " + sharedPreferences.getString("username", null));
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(intent);
            }
        });
    }
}
