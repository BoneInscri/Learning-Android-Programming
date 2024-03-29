package com.example.counter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;



public class CounterService extends Service {

    private Thread workThread;
    private int Time=0;
    @Override
    public void onCreate() {
        super.onCreate();
        workThread=new Thread(null,backgroundWork,"WorkThread");
    }

    @Override
    public void onStart(Intent intent,int startId)
    {
        super.onStart(intent,startId);
        if(!workThread.isAlive())
        {
            workThread.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        workThread.interrupt();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private Runnable backgroundWork=new Runnable() {
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    switch (MainActivity.Op)
                    {
                        case 0:
                            Time+=1;
                            break;
                        case 1:
                            break;
                        case -1:
                            Time=0;
                            break;
                    }
                    MainActivity.UpdateGUI(Time);
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    };

}
