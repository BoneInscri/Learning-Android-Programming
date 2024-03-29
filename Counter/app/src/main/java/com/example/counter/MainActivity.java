package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static Handler handler=new Handler();
    private static String Time_str;
    private static TextView display=null;
    public static int Op=0;
    public static void UpdateGUI(int t)
    {
        int h,m,s;
        h=t/3600;
        t=t%3600;
        m=t/60;
        t=t%60;
        s=t;
        String hh = String.valueOf(h);
        String mm = String.valueOf(m);
        String ss = String.valueOf(s);

        if(h<10)
            hh="0"+h;
        if(m<10)
            mm="0"+m;
        if(s<10)
            ss="0"+s;
        Time_str=hh+":"+mm+":"+ss;
        handler.post(RefreshTime);
    }
    public static Runnable RefreshTime=new Runnable(){
        @Override
        public void run() {
            display.setText(Time_str);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display=(TextView)findViewById(R.id.display);
        Button button_start = (Button) findViewById(R.id.start);
        Button button_stop = (Button) findViewById(R.id.stop);
        Button button_reset = (Button) findViewById(R.id.reset);
        final Intent serviceIntent=new Intent(this,CounterService.class);


        button_start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                startService(serviceIntent);
                Op=0;
            }
        });
        button_stop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Op=1;
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Op=-1;
            }
        });




    }
}