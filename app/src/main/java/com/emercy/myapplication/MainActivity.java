
package com.emercy.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

    public static final String CAMERA_PATH = "path";
    public static final String CAMERA_IMG = "img";

    private SurfaceView mSurfaceView;
    private Button mTakePhoto;
    private Camera mCamera = null;
    private SurfaceHolder.Callback mCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            startPreview();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            stopPreview();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPermission();

        bindViews();
    }

    /**
     * 获取权限
     */
    private void getPermission() {
        if (Build.VERSION.SDK_INT > 22) {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //先判断有没有权限 ，没有就在这里进行权限的申请
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            } else {
                //说明已经获取到摄像头权限了
                Log.i("Imooc Camera", "已经获取了权限");
            }
        } else {
            //这个说明系统版本在6.0之下，不需要动态获取权限。
            Log.i("Imooc Camera", "这个说明系统版本在6.0之下，不需要动态获取权限。");
        }
    }

    private void bindViews() {
        mSurfaceView = (SurfaceView) findViewById(R.id.sfv_preview);
        mTakePhoto = (Button) findViewById(R.id.btn_take);
        mSurfaceView.getHolder().addCallback(mCallback);

        mTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        String path;
                        if (TextUtils.isEmpty(path = savePhoto(data))) {
                            Intent it = new Intent(MainActivity.this, PhotoActivity.class);
                            it.putExtra(CAMERA_PATH, path);
                            startActivity(it);
                        } else {
                            Toast.makeText(MainActivity.this, "拍照失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private String savePhoto(byte[] bytes) {
        try {
            File file = File.createTempFile(CAMERA_IMG, "");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void startPreview() {
        mCamera = Camera.open();
        try {
            mCamera.setPreviewDisplay(mSurfaceView.getHolder());
            mCamera.setDisplayOrientation(90);   //让相机旋转90度
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopPreview() {
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }

}