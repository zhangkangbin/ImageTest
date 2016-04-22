package com.cayica.imagetest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by zhangkangbin on 2016/3/8.
 */
public class MyImageView extends ImageView {

    Context context;

    public MyImageView(Context context) {
        super(context);
        this.context=context;
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
/*        WindowManager a = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d1 = a.getDefaultDisplay(); // 获取屏幕宽、高用

        Point size = new Point();
        d1.getSize(size);
        int width = size.x;
        int height = size.y;

        int measuredHeight = measureHeight(heightMeasureSpec);
        int measuredWidth = measureWidth(widthMeasureSpec);
*//*
        Log.i("test","-------------measuredHeight--------:" + measuredHeight);
        Log.i("test","-----------height----------:" + height);
        Log.i("test", "widthMeasureSpec--" + measuredWidth);*/


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int measureHeight(int measureSpec) {

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

// Default size if no limits are specified.

        int result = 500;
        if (specMode == MeasureSpec.AT_MOST){

// Calculate the ideal size of your
// control within this maximum size.
// If your control fills the available
// space return the outer bound.

            result = specSize;
        }
        else if (specMode == MeasureSpec.EXACTLY){

// If your control can fit within these bounds return that value.
            result = specSize;
        }

        return result;
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

// Default size if no limits are specified.
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST){
// Calculate the ideal size of your control
// within this maximum size.
// If your control fills the available space
// return the outer bound.
            result = specSize;
        }

        else if (specMode == MeasureSpec.EXACTLY){
// If your control can fit within these bounds return that value.

            result = specSize;
        }

        return result;
    }
}
