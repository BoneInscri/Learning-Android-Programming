package com.example.final_assignment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class frag1 extends Fragment implements View.OnClickListener {

    EditText et_name,et_password;
    Button btn_login,btn_reg;
    ImageButton ib_close,ib_vis,ib_close2;
    TextView tv_forget;
    CheckBox cb_auto;

    private int Flag_auto=0;// 是否自动登录
    public frag1() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_frag1, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        et_name=view.findViewById(R.id.name);
        //et_name.setOnClickListener(this);

        et_password=view.findViewById(R.id.password);
        //et_password.setOnClickListener(this);

        btn_login=view.findViewById(R.id.login);
        btn_login.setOnClickListener(this);// 登录

        btn_reg=view.findViewById(R.id.reg);
        btn_reg.setOnClickListener(this);// 注册

        ib_close=view.findViewById(R.id.close);
        ib_close.setOnClickListener(this);// 清空

        ib_close2=view.findViewById(R.id.close2);
        ib_close2.setOnClickListener(this);

        ib_vis=view.findViewById(R.id.vis);
        ib_vis.setOnClickListener(this);// 可见

        tv_forget=view.findViewById(R.id.forget);
        tv_forget.setOnClickListener(this);// 忘记了密码

        cb_auto=view.findViewById(R.id.auto_login);
        // checked box 监听
        cb_auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Flag_auto=1;
                    checkRight(et_name.getText().toString(),et_password.getText().toString());
                }
                else
                {
                    Flag_auto=0;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login:
                checkRight(et_name.getText().toString(),et_password.getText().toString());
                break;
            case R.id.reg:
                Intent intent = new Intent(getActivity(), reg_activity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.close:
                et_name.setText("");
                break;
            case R.id.close2:
                et_password.setText("");
                break;
            case R.id.vis:
                setShowPassword(ib_vis, et_password);
                break;
            case R.id.forget:

                Tellpassword(et_name.getText().toString());
                break;
        }
    }

    // 提示密码
    private void Tellpassword(String name) {
        if(MainActivity.user_pass.containsKey(name))
            Toast.makeText(getActivity(),MainActivity.user_pass.get(name) ,Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(getActivity(),"该用户不存在",Toast.LENGTH_LONG).show();
        }
    }

    // 核实是否存在这个用户 和 验证是否密码正确
    private void checkRight(String name,String pass) {
        if(name==""||pass=="")
        {
            Toast.makeText(getActivity(), "帐号和密码不能为空", Toast.LENGTH_LONG).show();
        }
        else if(!MainActivity.user_pass.containsKey(name)){
            Toast.makeText(getActivity(), "不存在这个用户", Toast.LENGTH_LONG).show();
        }
        else if(!MainActivity.user_pass.get(name).equals(pass))
        {
            //Toast.makeText(getActivity(), pass, Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(),MainActivity.user_pass.get(name), Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(), "密码输入错误", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_LONG).show();
            MainActivity.Flag=1;
            MainActivity.viewPager.setScrollble(1);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == 1) {
                    String returnedData = data.getStringExtra("data_return");
                    String Name=data.getStringExtra("Name");
                    String Pass=data.getStringExtra("Pass");
                    //Toast.makeText(getActivity(), returnedData.toString(),
                      //      Toast.LENGTH_LONG).show();
                    et_name.setText(Name);
                    et_password.setText(Pass);
                    MainActivity.user_pass.put(Name,Pass);
                    Toast.makeText(getActivity(),"注册成功",
                            Toast.LENGTH_LONG).show();
                }
                break;
            default:

        }
    }

    // 密码是否可见
    private void setShowPassword(ImageButton mClickIv, EditText mClickEt) {
        int visibleType = InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD;
        if (mClickEt.getInputType() == visibleType) {
            mClickIv.setImageDrawable(getResources().getDrawable(R.drawable.visable_));
            mClickEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            mClickEt.setSelection(mClickEt.getText().toString().length());
        } else {
            mClickIv.setImageDrawable(getResources().getDrawable(R.drawable.visable));
            mClickEt.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mClickEt.setSelection(mClickEt.getText().toString().length());
        }
        return;
    }
}