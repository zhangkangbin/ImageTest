package com.cayica.imagetest.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;

/**
 * Created by zhangkangbin on 2016/4/14.
 */
public class MyCoordinatorLayout extends CoordinatorLayout {

    int mScreenHeight;
    private float oldY;

    public MyCoordinatorLayout(Context context) {
        super(context);

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        //标题70 。。底部 50
        mScreenHeight = metrics.heightPixels;//得到屏幕的宽度(像素)

        Log.i("test", "mScreenHeight" + mScreenHeight);
    }

    public MyCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        //标题70 。。底部 50
        mScreenHeight = metrics.heightPixels;//得到屏幕的宽度(像素)

        Log.i("test", "mScreenHeight" + mScreenHeight);
    }

    public MyCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        //标题70 。。底部 50
        mScreenHeight = metrics.heightPixels;//得到屏幕的宽度(像素)

        Log.i("test", "mScreenHeight" + mScreenHeight);
    }


    View root;
    private int t;

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        this.t = t;
        int count = getChildCount();

        mTarget2 = getChildAt(0);
        mTarget = getChildAt(1);



    }

    View mTarget;
    View mTarget2;


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
                //  Log.i("test", "--childHeight-" + padding);
              //  Log.i("test", "--111-" + canChildScrollUp());
                //Ys<0表示手指正在向上滑动，padding==mScreenHeight表示本scrollview已经滑动到了底部

                //  Log.i("test","padding"+padding+"--"+mScreenHeight+"YS--"+Ys);
                if (Ys < 0 && !canChildScrollUp()) { //到达底部
                    //让顶级的scrollview重新获得滑动事件
                    // LogUtil.i("让顶级的scrollview重新获得滑动事件");
                    Log.i("test", "--111-" + "可以上拉刷新");
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

    public boolean canChildScrollUp() {


        if (android.os.Build.VERSION.SDK_INT < 14) {

            return ViewCompat.canScrollVertically(mTarget, 1) || mTarget.getScrollY() > 0;
        } else {
            return ViewCompat.canScrollVertically(mTarget, 1);
        }
    }

    public boolean canChildScrollUp2() {


        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mTarget2 instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mTarget2;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mTarget2, -1) || mTarget2.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mTarget2, -1);
        }
    }

}
