package com.cayica.imagetest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by ysnow on 2015/3/3.
 */
public class ScrollViewPager extends ScrollView {
    private int mScreenHeight;
//  private int mOnePage;
//  private int mMenuPadding=220;


    private MyCoordinatorLayout wrapperMenu;
    private ScrollViewSub wrapperContent;
    private boolean isSetted = false;
    private boolean ispageOne = true;

    public ScrollViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获得屏幕的宽度和计算设置的偏移量的像素值,并计算出menu的宽度
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        mScreenHeight = metrics.heightPixels;//得到屏幕的宽度(像素)


    }

    public ScrollViewPager(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isSetted) {
            //得到里面的控件
            final LinearLayout wrapper = (LinearLayout) getChildAt(0);
            wrapperMenu = (MyCoordinatorLayout) wrapper.getChildAt(0);
            wrapperContent = (ScrollViewSub) wrapper.getChildAt(1);

            //设置两个子View的高度为手机的高度
            wrapperMenu.getLayoutParams().height = mScreenHeight -DensityUtil.dip2px(getContext(),20);
            wrapperContent.getLayoutParams().height = mScreenHeight-DensityUtil.dip2px(getContext(),20);
            isSetted = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(0, 0);
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                int scrollY = getScrollY();
                int creteria = mScreenHeight / 5;//滑动多少距离
                if (ispageOne) {
                    if (scrollY <= creteria) {
                        //显示菜单
                        this.smoothScrollTo(0, 0);
                       // Log.i("data", "显示网页菜单");
                    } else {
                        //隐藏菜单
                       // Log.i("data", "位于网页布局");
                        this.smoothScrollTo(0, mScreenHeight);
                        //  wrapperContent.loadUrl("http://wxv3.cayica.com/");
                        this.setFocusable(false);
                        ispageOne = false;
                        if(callBackLayout!=null){
                            callBackLayout.Layout(LAYOUT_MAIN);
                        }
                    }
                } else {
                    int scrollpadding = mScreenHeight - scrollY;
                    if (scrollpadding >= creteria) {
                        this.smoothScrollTo(0, 0);
                        ispageOne = true;

                       // Log.i("data", "位于主布局");

                        if(callBackLayout!=null){
                            callBackLayout.Layout(LAYOUT_SUB);
                        }

                    } else {
                      //  Log.i("data", "显示主布局菜单");
                        this.smoothScrollTo(0, mScreenHeight);
                        // wrapperContent.loadUrl("http://wxv3.cayica.com/");
                    }
                }

                return true;
        }
        return super.onTouchEvent(ev);
    }

    public void setSildingLayout(CallBackLayout callBackLayout) {

        this.callBackLayout = callBackLayout;

    }

    private CallBackLayout callBackLayout;

    public interface CallBackLayout {
        void Layout(int i);

    }

    public final  int LAYOUT_MAIN=0; //主布局
    public final  int LAYOUT_SUB=1;//子布局
}
