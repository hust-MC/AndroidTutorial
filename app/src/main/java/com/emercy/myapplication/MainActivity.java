
package com.emercy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    float xAxis = 0f;
    float yAxis = 0f;

    float downXAxis = 0f;
    float downYAxis = 0f;

    TextView downX, downY, moveX, moveY;
    TextView touch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downX = findViewById(R.id.down_x);
        downY = findViewById(R.id.down_y);
        moveX = findViewById(R.id.move_x);
        moveY = findViewById(R.id.move_y);

        touch = findViewById(R.id.touch);

        touch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int actionPeformed = event.getAction();

                switch (actionPeformed) {
                    case MotionEvent.ACTION_DOWN: {
                        downXAxis = event.getX();
                        downYAxis = event.getY();

                        downX.setText("按下的位置横坐标：" + downXAxis);
                        downY.setText("按下的位置纵坐标：" + downYAxis);
                        break;
                    }

                    case MotionEvent.ACTION_MOVE: {
                        final float x = event.getX();
                        final float y = event.getY();

                        final float dx = x - downXAxis;
                        final float dy = y - downYAxis;

                        xAxis += dx;
                        yAxis += dy;

                        moveX.setText("移动距离的横坐标：" + xAxis);
                        moveY.setText("移动距离的纵坐标：" + yAxis);
                        break;
                    }
                }
                return true;
            }
        });
    }
}
