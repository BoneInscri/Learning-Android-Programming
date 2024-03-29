package com.example.final_assignment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class MyViewpager extends ViewPager{
    private int scrollble=0;

    public MyViewpager(Context context){
        super(context);
    }

    public MyViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (scrollble==0) {
            return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (scrollble == 0) {
            return false;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    public int isScrollble() {
        return scrollble;
    }

    public void setScrollble(int scrollble) {
        this.scrollble = scrollble;
    }
}
