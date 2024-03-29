package org.hdbone.reading_video;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static MyViewPager viewPager;
    private List<Fragment> MyFragmentList;
    private LinearLayout ll_home,ll_reading,ll_party,ll_mine;
    private ImageView iv_home,iv_reading,iv_party,iv_mine,iv_current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabs();

        // 构造适配器
        List <Fragment>fragments =new ArrayList<>();
        fragments.add(new home_frag());
        fragments.add(new reading_frag());
        fragments.add(new party_frag());
        fragments.add(new mine_frag());
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
        iv_home=findViewById(R.id.img_home);
        iv_home.setOnClickListener(this);
        ll_home=findViewById(R.id.bottom_home);
        ll_home.setOnClickListener(this);

        iv_party=findViewById(R.id.img_party);
        iv_party.setOnClickListener(this);
        ll_party=findViewById(R.id.bottom_party);
        ll_party.setOnClickListener(this);

        iv_reading=findViewById(R.id.img_reading);
        iv_reading.setOnClickListener(this);
        ll_reading=findViewById(R.id.bottom_reading);
        ll_reading.setOnClickListener(this);

        iv_mine=findViewById(R.id.img_mine);
        iv_mine.setOnClickListener(this);
        ll_mine=findViewById(R.id.bottom_mine);
        ll_mine.setOnClickListener(this);

        iv_home.setSelected(true);
        iv_current=iv_home;
    }


    // 自定义适配器
    class MyFrameAdapter extends FragmentStatePagerAdapter {

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

    @Override
    public void onClick(View v) {
        changeTab(v.getId());
    }

    private void changeTab(int position) {
        iv_current.setSelected(false);
        switch (position)
        {
            case R.id.img_home:
                viewPager.setCurrentItem(0);
            case 0:
                iv_home.setSelected(true);
                iv_current=iv_home;
                break;
            case R.id.img_reading:
                viewPager.setCurrentItem(1);
            case 1:
                iv_reading.setSelected(true);
                iv_current=iv_reading;
                break;
            case R.id.img_party:
                viewPager.setCurrentItem(2);
            case 2:
                iv_party.setSelected(true);
                iv_current=iv_party;
                break;
            case R.id.img_mine:
                viewPager.setCurrentItem(3);
            case 3:
                iv_mine.setSelected(true);
                iv_current=iv_mine;
                break;
        }
    }
}