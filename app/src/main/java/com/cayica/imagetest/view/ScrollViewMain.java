package com.cayica.imagetest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ScrollView;


/**
 * Created by ysnow on 2015/4/20.
 */
public class ScrollViewMain extends ScrollView {
    private int mScreenHeight;
    public float oldY;
    private int t;

    public ScrollViewMain(Context context) {
        this(context, null);
    }

    public ScrollViewMain(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollViewMain(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        //标题70 。。底部 50
        mScreenHeight = metrics.heightPixels;//得到屏幕的(像素)
        //LogUtil.i("ddd----"+DensityUtil.dip2px(getContext(),50+70));
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //在滑动的时候获得当前值，并计算得到YS,用来判断是向上滑动还是向下滑动
                float Y = ev.getY();
                float Ys = Y - oldY;

                //得到scrollview里面空间的高度
                int childHeight = this.getChildAt(0).getMeasuredHeight();
                //子控件高度减去scrollview向上滑动的距离
                int padding = childHeight - t;
                //Ys<0表示手指正在向上滑动，padding==mScreenHeight表示本scrollview已经滑动到了底部

                //  Log.i("test","padding"+padding+"--"+mScreenHeight+"YS--"+Ys);
                if (Ys < 0 && padding == mScreenHeight) { //到达底部
                    //让顶级的scrollview重新获得滑动事件
                    // LogUtil.i("让顶级的scrollview重新获得滑动事件");
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_DOWN:
                //手指按下的时候，获得滑动事件，也就是让顶级scrollview失去滑动事件
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                //LogUtil.i("也就是让顶级scrollview失去滑动事件");
                //并且记录Y点值
                oldY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                // LogUtil.i("ACTION_UP 也就是让顶级scrollview失去滑动事件");
                break;


        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        //t表示本scrollview向上滑动的距离
        this.t = t;
        super.onScrollChanged(l, t, oldl, oldt);
    }


}
