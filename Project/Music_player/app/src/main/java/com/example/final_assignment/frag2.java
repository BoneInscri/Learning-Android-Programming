package com.example.final_assignment;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class frag2 extends Fragment implements View.OnClickListener{

    private List<String> music_list=new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private Spinner spinner_music;
    private ImageView iv_disk;// 光盘的旋转
    private static SeekBar musicProgressBar;// 进度条
    private static TextView currentTime,totalTime;// 当前时间和总时间
    private Button btn_play,btn_pause,btn_continue;//播放 暂停 继续
    private ObjectAnimator animator;// 动图
    public frag2() {

    }

    private MediaPlayerServices.MusicControl control;
    private ServiceConnection cnn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            control=(MediaPlayerServices.MusicControl) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_frag2, container, false);
        init(view);
        spinner_init(view);

        return view;

    }

    private void spinner_init(View view) {
        music_list = Arrays.asList(getResources().getStringArray(R.array.music));
        spinner_music.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if(MainActivity.Flag==1){
                Toast.makeText(getActivity(), "现在播放的是:"+ music_list.get(pos),Toast.LENGTH_LONG ).show();
                MediaPlayerServices.music_opt=pos;}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // 初始化
    public void init(View view)
    {
        spinner_music=view.findViewById(R.id.music_sel);

        iv_disk=view.findViewById(R.id.disk);
        iv_disk.setOnClickListener(this);

        musicProgressBar=view.findViewById(R.id.process_bar);
        musicProgressBar.setOnClickListener(this);

        currentTime=view.findViewById(R.id.current_Time);
        currentTime.setOnClickListener(this);

        totalTime=view.findViewById(R.id.total_Time);
        totalTime.setOnClickListener(this);

        btn_play=view.findViewById(R.id.Start);
        btn_play.setOnClickListener(this);

        btn_continue=view.findViewById(R.id.Continue);
        btn_continue.setOnClickListener(this);

        btn_pause=view.findViewById(R.id.Pause);
        btn_pause.setOnClickListener(this);

        animator = ObjectAnimator.ofFloat(iv_disk,"rotation",0,360.0F);
        animator.setDuration(10000);// 10s 一圈 （ms）
        animator.setInterpolator(new LinearInterpolator());// 匀速旋转
        animator.setRepeatCount(-1);// 一直转动

        // 信息传递
        Intent intent=new Intent(this.getActivity(),MediaPlayerServices.class);
        //***********
        this.getActivity().bindService(intent,cnn, Context.BIND_AUTO_CREATE);


        musicProgressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // 更改 （用户拖动）
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress==seekBar.getMax())// 达到最大值
                {
                    animator.pause();
                }
                if(fromUser)
                {
                    control.seekTo(progress);// 跳转到用户
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                control.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                control.resume();
            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Start:
                // 播放音乐
                control.play();
                animator.start();
                Record_history();
                break;
            case R.id.Continue:
                // 继续播放
                control.resume();
                animator.resume();
                break;
            case R.id.Pause:
                // 暂停
                control.pause();
                animator.pause();
                break;
        }
    }

    // 保持播放记录
    private void Record_history() {

        FileOutputStream fos =null;
        try {
            fos= getActivity().openFileOutput(MainActivity.filename,Context.MODE_APPEND);

            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            String str=formatter.format(date);
            str=str+"\n"+(music_list.get(MediaPlayerServices.music_opt)+"\n\n\n");
            fos.write((str).getBytes());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                if(fos!=null)
                {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        control.stop();
        this.getActivity().unbindService(cnn);
    }
    // 一个静态处理器  handler
    public static Handler handler=new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle =msg.getData();
            int duration=bundle.getInt("duration");// 总时间
            int currentPos=bundle.getInt("currentPos");// 当前的时间

            musicProgressBar.setMax(duration);
            musicProgressBar.setProgress(currentPos);
            String totalTime_=msToMinSec(duration);
            String currentTime_=msToMinSec(currentPos);
            totalTime.setText(totalTime_);
            currentTime.setText(currentTime_);
        }
    };
    // ms 变秒
    public static String msToMinSec(int ms)
    {
        int sec=ms/1000;
        int min=sec/60;
        sec-=min*60;
        return String.format("%02d:%02d",min,sec);
    }
}
