
package com.emercy.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
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
        mViewFlipper = this.findViewById(R.id.view_flipper);

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mViewFlipper.setAutoStart(true);
                mViewFlipper.setFlipInterval(2000);
                mViewFlipper.startFlipping();
                Toast.makeText(MainActivity.this,
                        "Automatic view flipping has started", Toast.LENGTH_SHORT).show();
            }
        });


        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewFlipper.stopFlipping();
                Toast.makeText(MainActivity.this,
                        "Automatic view flipping has stopped", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = touchEvent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = touchEvent.getX();
                if (initialX > finalX) {
                    if (mViewFlipper.getDisplayedChild() == 2)
                        break;

                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));

                    mViewFlipper.showNext();
                } else {
                    if (mViewFlipper.getDisplayedChild() == 0)
                        break;

                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_out));

                    mViewFlipper.showPrevious();
                }
                break;
        }
        return false;
    }
}
