package org.hdbone.reading_video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class MyViewPager extends ViewPager {
    private int scrollble=0;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context,AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (scrollble==0) {
            return true;
        }
        return super.onTouchEvent(ev);
    }

    public int getScrollble() {
        return scrollble;
    }

    public void setScrollble(int scrollble) {
        this.scrollble = scrollble;
    }
}
