package com.emercy.myapplication;

import android.view.View;
import android.view.ViewGroup;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<Model> {

    Context mContext;
    int layoutResourceId;
    Model data[];

    public MyAdapter(Context mContext, int layoutResourceId, Model[] data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

        Model folder = data[position];


        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);

        return listItem;
    }
}
