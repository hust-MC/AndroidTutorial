
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
    EditText name, loc, desig;
    Button saveBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.txtName);
        loc = (EditText) findViewById(R.id.txtLocation);
        desig = (EditText) findViewById(R.id.txtDesignation);
        saveBtn = (Button) findViewById(R.id.btnSave);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String location = loc.getText().toString();
                String designation = desig.getText().toString();
                DbHelper DbHelper = new DbHelper(MainActivity.this);
                DbHelper.insertUserDetails(username, location, designation);
                intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "英雄信息登记成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}