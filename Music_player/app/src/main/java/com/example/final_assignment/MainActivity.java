package com.example.final_assignment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.app.TabActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static MyViewpager viewPager;
    public static String filename="history.txt";
    private List<Fragment> MyFragmentList;
    private LinearLayout ll_music,ll_history,ll_login;
    private ImageView iv_music,iv_history,iv_login,iv_current;
    public static int Flag=0;
    public static Map<String,String> user_pass;// 映射
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabs();// 初始化三个按键

        user_pass=new HashMap<>();
        // 构造适配器
        List <Fragment>fragments =new ArrayList<>();
        fragments.add(new frag1());
        fragments.add(new frag2());
        fragments.add(new frag3());
        MyFrameAdapter myFrameAdapter=new MyFrameAdapter(getSupportFragmentManager(),
                0,fragments);
        viewPager=findViewById(R.id.viewPager);
        viewPager.setAdapter(myFrameAdapter);

        // 页面切换监听器
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTab(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initTabs()
    {
        iv_login=findViewById(R.id.im_login);
        iv_login.setOnClickListener(this);
        ll_login=findViewById(R.id.login);
        ll_login.setOnClickListener(this);

        iv_music=findViewById(R.id.im_music);
        iv_music.setOnClickListener(this);
        ll_music=findViewById(R.id.music);
        ll_music.setOnClickListener(this);

        iv_history=findViewById(R.id.im_history);
        iv_history.setOnClickListener(this);
        ll_history=findViewById(R.id.history);
        ll_history.setOnClickListener(this);

        iv_login.setSelected(true);
        iv_current=iv_login;
    }
    private void changeTab(int position) {
        iv_current.setSelected(false);
        switch (position)
        {
            case R.id.im_login:
                viewPager.setCurrentItem(0);
            case 0:
                iv_login.setSelected(true);
                iv_current=iv_login;
                break;
            case R.id.im_music:
                viewPager.setCurrentItem(1);
            case 1:
                iv_music.setSelected(true);
                iv_current=iv_music;
                break;
            case R.id.im_history:
                viewPager.setCurrentItem(2);
            case 2:
                iv_history.setSelected(true);
                iv_current=iv_history;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(Flag==0)
        {
            Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_LONG).show();
            return ;
        }
        if(Flag==1&&(v.getId()==R.id.im_login||v.getId()==R.id.im_music||v.getId()==R.id.im_history))
        changeTab(v.getId());
    }

    // 自定义适配器
   class MyFrameAdapter extends FragmentStatePagerAdapter{

        public MyFrameAdapter(@NonNull FragmentManager fm, int behavior,
                              List<Fragment> fragments) {
            super(fm, behavior);
            MyFragmentList=fragments;
        }

        // 刷新界面
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return MyFragmentList.get(position);
        }
        // 几个界面
        @Override
        public int getCount() {
            return MyFragmentList.size();
        }
    }


}