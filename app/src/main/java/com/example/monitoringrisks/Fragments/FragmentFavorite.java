package com.example.monitoringrisks.Fragments;

import androidx.fragment.app.Fragment;

public class FragmentFavorite extends Fragment {
    static private FragmentFavorite instance;

    public static FragmentFavorite getInstance() {
        if(instance==null){
            instance = new FragmentFavorite();
        }
        return instance;
    }
    private FragmentFavorite(){

    }
}
