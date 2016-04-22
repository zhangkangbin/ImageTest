package com.cayica.imagetest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import android.widget.GridView;

/**
 * Created by zhangkangbin on 2016/4/7.
 */
public class MyGridView extends GridView {

    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setNumColumns(int numColumns) {
        Log.i("test","---"+numColumns);
        super.setNumColumns(numColumns);
    }

    public int getNumColumns() {

       // Log.i("test",""+super.getNumColumns());
        return super.getNumColumns();
    }
}
