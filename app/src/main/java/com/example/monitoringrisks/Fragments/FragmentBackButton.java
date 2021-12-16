package com.example.monitoringrisks.Fragments;


import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.monitoringrisks.Activities.ActivityAES;

import com.example.monitoringrisks.R;

public class FragmentBackButton extends Fragment {
    public static Fragment feedorfavorite;
    public static Fragment AEStemp;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_backbutton,container,false);
        view.findViewById(R.id.backbut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityAES.getInstance().finish();
            }
        });
        return view;
    }
}

