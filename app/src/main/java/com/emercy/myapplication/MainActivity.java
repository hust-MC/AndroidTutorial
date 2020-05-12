
package com.emercy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1.设置布局文件并从布局文件中拿到 ExpandableListView 实例；
        setContentView(R.layout.activity_main);
        ExpandableListView listView = findViewById(R.id.expandableListView);
        // 2. 获取数据集（实际使用中可能是从网络获取或者本地读取）
        expandableListDetail = DataCollection.getData();
        final List<String> heroCategory = new ArrayList<>(expandableListDetail.keySet());
        // 3. 创建适配器，并为 ExpandableListView 实例设置适配器
        ExpandableListAdapter adapter = new MyExpandableListAdapter(this, heroCategory, expandableListDetail);
        listView.setAdapter(adapter);
        // 4. 为 ExpandableListView 添加相应的事件监听器，并实现监听器接口中的回调方法
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), heroCategory.get(groupPosition)
                                + " 列表展开", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), heroCategory.get(groupPosition)
                                + " 列表折叠", Toast.LENGTH_SHORT).show();

            }
        });

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), heroCategory.get(groupPosition)
                        + " -> " + expandableListDetail.get(heroCategory.get(groupPosition))
                        .get(childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }

}
