package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView Output;
    private TextView Input;
    Calculator calculator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ui);
        Input=(TextView) findViewById(R.id.input);
        Output=(TextView)findViewById(R.id.output);
        Button num1=(Button) findViewById(R.id.num1);
        Button num2=(Button) findViewById(R.id.num2);
        Button num3=(Button) findViewById(R.id.num3);
        Button num4=(Button) findViewById(R.id.num4);
        Button num5=(Button) findViewById(R.id.num5);
        Button num6=(Button) findViewById(R.id.num6);
        Button num7=(Button) findViewById(R.id.num7);
        Button num8=(Button) findViewById(R.id.num8);
        Button num9=(Button) findViewById(R.id.num9);
        Button num0=(Button) findViewById(R.id.num0);
        Button add=(Button) findViewById(R.id.add);
        Button sub=(Button) findViewById(R.id.sub);
        Button mul=(Button) findViewById(R.id.mul);
        Button div=(Button) findViewById(R.id.div);
        Button equal=(Button) findViewById(R.id.equal);
        Button point=(Button) findViewById(R.id.point);
        Button per=(Button) findViewById(R.id.per);
        Button reset=(Button) findViewById(R.id.Reset);
        Button delete=(Button) findViewById(R.id.Del);

        calculator =new Calculator();
        View.OnClickListener buttonlistener=new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String show=((Button)v).getText().toString();
                calculator.process(show);
                String Num1=calculator.getNum1();
                String Num2=calculator.getNum2();
                String Op=calculator.getOp();
                String Ans=calculator.getAns();
                if(Ans.length()>13)
                    Output.setTextSize(30);
                else
                    Output.setTextSize(50);
                if((Num1+Op+Num2).length()>13)
                    Input.setTextSize(30);
                else
                    Input.setTextSize(50);


                Input.setText(Num1+Op+Num2);
                Output.setText(Ans);
            }
        };
        num0.setOnClickListener(buttonlistener);
        num1.setOnClickListener(buttonlistener);
        num2.setOnClickListener(buttonlistener);
        num3.setOnClickListener(buttonlistener);
        num4.setOnClickListener(buttonlistener);
        num5.setOnClickListener(buttonlistener);
        num6.setOnClickListener(buttonlistener);
        num7.setOnClickListener(buttonlistener);
        num8.setOnClickListener(buttonlistener);
        num9.setOnClickListener(buttonlistener);
        add.setOnClickListener(buttonlistener);
        sub.setOnClickListener(buttonlistener);
        mul.setOnClickListener(buttonlistener);
        div.setOnClickListener(buttonlistener);
        equal.setOnClickListener(buttonlistener);
        point.setOnClickListener(buttonlistener);
        per.setOnClickListener(buttonlistener);
        reset.setOnClickListener(buttonlistener);
        delete.setOnClickListener(buttonlistener);
    }
}