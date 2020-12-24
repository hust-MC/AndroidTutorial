
package com.emercy.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView button = (ImageView) findViewById(R.id.image);
        InputStream resource = getResources().openRawResource(R.raw.avatar);
        Bitmap bitmap = BitmapFactory.decodeStream(resource);
        button.setBackground(new RoundCornerDrawable(bitmap));
    }
}