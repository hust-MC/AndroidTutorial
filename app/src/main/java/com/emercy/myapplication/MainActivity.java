
package com.emercy.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.viewpager.widget.ViewPager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private ViewPager mViewPager;
    private String[] mTitle = new String[]{"苹果", "香蕉", "荔枝"};
    private List<View> mView = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = findViewById(R.id.text);
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv.setText(mTitle[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ImageView view1 = (ImageView) LayoutInflater.from(this).inflate(R.layout.list_item, null);
        view1.setBackgroundColor(Color.RED);
        view1.setImageResource(R.drawable.apple);
        mView.add(view1);

        ImageView view2 = (ImageView) LayoutInflater.from(this).inflate(R.layout.list_item, null);
        view2.setBackgroundColor(Color.GREEN);
        view2.setImageResource(R.drawable.banana);
        mView.add(view2);

        ImageView view3 = (ImageView) LayoutInflater.from(this).inflate(R.layout.list_item, null);
        view3.setBackgroundColor(Color.BLUE);
        view3.setImageResource(R.drawable.lychee);
        mView.add(view3);

        mViewPager.setAdapter(new MyAdapter(mView));
        tv.setText(mTitle[0]);
    }
}
