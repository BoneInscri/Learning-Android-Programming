package com.example.final_assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class reg_activity extends Activity implements View.OnClickListener{
    Button btn_yes;
    EditText editText1;
    EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_layout);
        init();
    }

    private void init() {
        btn_yes = findViewById(R.id.yes);
        btn_yes.setOnClickListener(this);

        editText1 = findViewById(R.id.name2);
        editText2 = findViewById(R.id.password2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.yes:
                Finish();
                break;
        }
    }

    private void Finish() {
        String username=editText1.getText().toString();
        String password=editText2.getText().toString();
        String uriString="用户名："+username+"\n"+"密码："+password;
        Intent intent=new Intent();
        intent.putExtra("data_return",uriString);
        intent.putExtra("Name",username);
        intent.putExtra("Pass",password);


        if((username==null||username.length()<=0)&&(password==null||password.length()<=0)){
            Toast.makeText(getApplicationContext(),"用户名和密码为空",
                    Toast.LENGTH_LONG).show();
        }
        else if(!isAlphaNumeric(username))
        {
            Toast.makeText(getApplicationContext(),"用户名只能含字母和数字",
                    Toast.LENGTH_LONG).show();
        }
        else if(MainActivity.user_pass.containsKey(username))
        {
            Toast.makeText(getApplicationContext(),"该用户名已经有了",
                    Toast.LENGTH_LONG).show();
        }
        else{
            setResult(1,intent);
            finish();
        }
    }
    // 是否由数字和字母组成
    public boolean isAlphaNumeric(String s){
        Pattern p = Pattern.compile("[0-9a-zA-Z]{1,}");
        Matcher m = p.matcher(s);
        return m.matches();
    }
}
