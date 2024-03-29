package com.example.final_assignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class frag3 extends Fragment implements View.OnClickListener {

    TextView tv_title,tv_display;
    public frag3() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_frag3, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        tv_title= view.findViewById(R.id.History);
        tv_title.setOnClickListener(this);

        tv_display=view.findViewById(R.id.display);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.History:
                display_history();
                break;
        }
    }
    // 展示历史记录
    private void display_history() {
        String str="";
        FileInputStream fis=null;
        try {
            fis= getActivity().openFileInput(MainActivity.filename);
            byte[] buffer=new byte[fis.available()];
            fis.read(buffer);
            str=new String(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis!=null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        tv_display.setText(str);
    }
}