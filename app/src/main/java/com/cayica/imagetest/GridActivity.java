package com.cayica.imagetest;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private static final String TAG = "test";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid);


        GridView gridView = (GridView) findViewById(R.id.grid);

        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        GridAdapter myGridView = new GridAdapter(getString());
        gridView.getNumColumns();
        gridView.setAdapter(myGridView);

    }

    private List<String> getString() {


        List<String> strings = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            strings.add("--" + i);
        }


        return strings;

    }
}
