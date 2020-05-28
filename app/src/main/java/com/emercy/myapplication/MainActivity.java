
package com.emercy.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class MainActivity extends Activity {

    private ViewFlipper mViewFlipper;
    private Context mContext;
    private float initialX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mViewFlipper = findViewById(R.id.view_flipper);

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewFlipper.setAutoStart(true);
                mViewFlipper.setInAnimation(mContext, R.anim.in_from_right);
                mViewFlipper.setOutAnimation(mContext, R.anim.out_from_left);
                mViewFlipper.setFlipInterval(2000);
                mViewFlipper.startFlipping();
                Toast.makeText(MainActivity.this,
                        "启动自动播放", Toast.LENGTH_SHORT).show();
            }
        });


        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewFlipper.stopFlipping();
                Toast.makeText(MainActivity.this,
                        "停止自动播放", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 记录滑动初始坐标
                initialX = touchEvent.getX();
                break;
            case MotionEvent.ACTION_UP:
                // 记录滑动结束坐标
                float finalX = touchEvent.getX();
                if (initialX > finalX) {
                    // 初始坐标大于结束坐标，说明为左滑，则播放下一页
                    if (mViewFlipper.getDisplayedChild() != 2) {
                        mViewFlipper.setInAnimation(mContext, R.anim.in_from_right);
                        mViewFlipper.setOutAnimation(mContext, R.anim.out_from_left);
                        mViewFlipper.showNext();
                    }
                } else {
                    // 初始坐标不大于结束坐标，说明为右滑，则播放上一页
                    if (mViewFlipper.getDisplayedChild() != 0) {
                        mViewFlipper.setInAnimation(mContext, R.anim.in_from_left);
                        mViewFlipper.setOutAnimation(mContext, R.anim.out_from_right);
                        mViewFlipper.showPrevious();
                    }
                }
                break;
        }
        return false;
    }
}
