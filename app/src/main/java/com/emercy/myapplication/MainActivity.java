
package com.emercy.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    static ByteArrayOutputStream byteOut = null;
    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_cut = (Button) findViewById(R.id.button);
        btn_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capture();
            }
        });
    }

    public void capture() {
        Runnable action = new Runnable() {
            @Override
            public void run() {
                final View contentView = getWindow().getDecorView();
                try {
                    bitmap = Bitmap.createBitmap(contentView.getWidth(),
                            contentView.getHeight(), Bitmap.Config.ALPHA_8);
                    contentView.draw(new Canvas(bitmap));
                    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteOut);
                    save(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (null != byteOut)
                            byteOut.close();
                        if (null != bitmap && !bitmap.isRecycled()) {
                            bitmap = null;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        try {
            action.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void save(Bitmap b) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("sdcard/short.png");
            boolean success = b.compress(Bitmap.CompressFormat.PNG, 80, fos);
            fos.flush();
            fos.close();
            if (success) {
                Toast.makeText(MainActivity.this, "截图完成", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}