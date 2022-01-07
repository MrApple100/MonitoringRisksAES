package com.example.monitoringrisks.Fragments.Pages;

import androidx.fragment.app.Fragment;


public class FragmentCompare extends Fragment {
    static private FragmentCompare instance;

    public static FragmentCompare getInstance() {
        if(instance==null){
            instance = new FragmentCompare();
        }
        return instance;
    }
    private FragmentCompare(){

    }

}
