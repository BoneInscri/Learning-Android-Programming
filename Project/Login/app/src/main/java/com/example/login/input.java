package com.example.login;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class input extends Activity{

    EditText editText1;
    EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_name_password);
        editText1=(EditText) findViewById(R.id.username);
        editText2=(EditText) findViewById(R.id.password);
    }
    @Override
    public void onBackPressed(){
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
            finish();
        }
        else{
            setResult(RESULT_OK,intent);
            finish();
        }
    }

}
