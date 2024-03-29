package com.example.final_assignment;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class MediaPlayerServices extends Service {

    private MediaPlayer mediaPlayer;// 多媒体
    private Timer timer;// 时钟
    public static int music_opt=0;
    public MediaPlayerServices()
    {

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicControl();// 绑定服务 音乐操作 实例化
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer =new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void addTimer()
    {
        if(timer==null)
        {
            timer=new Timer();
            TimerTask task =new TimerTask() {
                @Override
                public void run() {
                    int duration=mediaPlayer.getDuration();
                    int currentPos=mediaPlayer.getCurrentPosition();
                    Message msg=frag2.handler.obtainMessage();
                    Bundle bundle =new Bundle();
                    bundle.putInt("duration",duration);
                    bundle.putInt("currentPos",currentPos);
                    msg.setData(bundle);
                    frag2.handler.sendMessage(msg);
                }
            };
            timer.schedule(task,5,500);
        }

    }


    // 音乐控制
    class MusicControl extends Binder{
        // 播放
        public void play()
        {
            mediaPlayer.reset();
            switch(music_opt){
                case 0:
                    mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.music1);
                    break;
                case 1:
                    mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.music2);
                    break;
            }
            mediaPlayer.start();
            addTimer();
        }
        //暂停
        public void pause()
        {
            mediaPlayer.pause();
        }
        //继续播放
        public void resume()
        {
            mediaPlayer.start();
        }
        // 停止
        public void stop()
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            try {
                timer.cancel();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        // 指定时间点 播放
        public void seekTo(int ms)
        {
            mediaPlayer.seekTo(ms);
        }

    }

}
