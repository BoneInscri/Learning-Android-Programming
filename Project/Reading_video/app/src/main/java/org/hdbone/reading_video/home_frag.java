package org.hdbone.reading_video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;


public class home_frag extends Fragment implements View.OnClickListener {


    private ImageButton imb_follow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.home_page, container, false);
        init(view);


        return view;
    }
    private void init(View view)
    {
        imb_follow=view.findViewById(R.id.follow);
        imb_follow.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.follow){
            imb_follow.setSelected(true);
            imb_follow.setScaleX(1);
            imb_follow.setScaleY(1);
        }
    }
}
