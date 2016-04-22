package com.cayica.imagetest;

import android.app.ActionBar;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="test" ;



    HandlerThread handlerThread=new HandlerThread(""){
        @Override
        public void run() {
            super.run();
        }
    };
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.i(TAG,"Callback "+msg.what);

            return false;
        }
    }){

        @Override
        public void handleMessage(Message msg) {

            Log.i(TAG,"handleMessage"+msg.what);
            super.handleMessage(msg);
        }

        @Override
        public void dispatchMessage(Message msg) {

            Log.i(TAG,"dispatchMessage"+msg.what);
            super.dispatchMessage(msg);
        }
    };

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            Log.i(TAG,"runnable------");
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myviewgroup);
/*
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler.sendEmptyMessage(6);

              handler.postDelayed(runnable,1000);

            }
        });*/


    }
}
