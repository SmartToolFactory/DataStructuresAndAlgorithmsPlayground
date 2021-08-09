package com.smarttoolfactory.datastructuresandalgorithmsplayground;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences sharedPreferences = null;
        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.apply();

        View contentLayout = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);



        RelativeLayout myView = findViewWithId((ViewGroup) contentLayout, R.id.relative_layout);
        System.out.println("🍏 MY VIEW is: " + myView);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                myView.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });

        thread.start();
    }


    public <T extends View> T findViewWithId(ViewGroup root, int id) {

        if (root == null || id < 0) return null;

        if (root.getId() == id) return (T) root;

        int childCount = root.getChildCount();

        for (int i = 0; i < childCount; i++) {

            View child = root.getChildAt(i);

            if (child.getId() == id) return (T) child;

            if (child instanceof ViewGroup) {
                ViewGroup childViewGroup = (ViewGroup) child;
                ViewGroup res = findViewWithId(childViewGroup, id);
                if (res == null) return (T) res;
            }
        }
        return null;
    }
}
