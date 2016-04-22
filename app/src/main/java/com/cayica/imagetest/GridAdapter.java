package com.cayica.imagetest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhangkangbin on 2016/4/7.
 */
public class GridAdapter extends BaseAdapter {


    List<String> stringList;

    public GridAdapter(List<String> stringList) {

        this.stringList = stringList;

    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }


    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_test, null);
        TextView textView = (TextView) view.findViewById(R.id.button);
        textView.setText(stringList.get(position));


        return view;
    }
}
