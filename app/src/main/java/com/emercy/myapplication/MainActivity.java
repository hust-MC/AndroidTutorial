
package com.emercy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.drawerlayout.widget.DrawerLayout;


public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ImageView mImage;

    Model[] mItem = new Model[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mImage = findViewById(R.id.imageview);

        mItem[0] = new Model(R.drawable.apple, "苹果");
        mItem[1] = new Model(R.drawable.peach, "桃子");
        mItem[2] = new Model(R.drawable.watermelon, "西瓜");

        MyAdapter adapter = new MyAdapter(this, R.layout.list_item, mItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mImage.setImageResource(mItem[position].icon);
            }
        });
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


    }

}
