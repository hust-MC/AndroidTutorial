//package com.emercy.myapplication;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//public class MyAdapter extends BaseAdapter {
//
//    private Context mContext;
//    private String[] mName;
//    private int[] mResId;
//
//    public MyAdapter(Context context) {
//        mContext = context;
//    }
//
//    public void setData(String[] name, int[] resId) {
//        mName = name;
//        mResId = resId;
//    }
//
//
//    @Override
//    public int getCount() {
//        return mName.length * 10;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // 针对convertView做一个简单的优化
//        if (convertView == null) {
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_view, null);
//        }
//        TextView name = convertView.findViewById(R.id.textView);
//        ImageView image = convertView.findViewById(R.id.imageView);
//        name.setText(mName[position % mName.length]);
//        image.setImageResource(mResId[position % mResId.length]);
//        return convertView;
//    }
//}
