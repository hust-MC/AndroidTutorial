package com.emercy.myapplication;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class PhotoActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView img = new ImageView(this);
        String path = getIntent().getStringExtra("path");
        if (path != null) {
            img.setImageURI(Uri.fromFile(new File(path)));
        }
        setContentView(img);
    }
}