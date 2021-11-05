package com.example.monitoringrisks.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.monitoringrisks.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class NavigationFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    static private NavigationFragment instance;

    public static NavigationFragment getInstance() {
        if(instance==null){
            instance = new NavigationFragment();
        }
        return instance;
    }
    private NavigationFragment(){

    }
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navigationpanel,container,false);
        bottomNavigationView = view.findViewById(R.id.nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.ToFeed);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
    FragmentFeed fragmentFeed=FragmentFeed.getInstance();
    FragmentFavorite fragmentFavorite=FragmentFavorite.getInstance();
    FragmentProfile fragmentProfile=FragmentProfile.getInstance();
    FragmentCompare fragmentCompare=FragmentCompare.getInstance();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (item.getItemId()) {
            case R.id.ToFavorite:
                getFragmentManager().beginTransaction().replace(R.id.container, fragmentFavorite).commit();
                return true;

            case R.id.ToFeed:
                getFragmentManager().beginTransaction().replace(R.id.container, fragmentFeed).commit();
                return true;

            case R.id.ToCompare:
                getFragmentManager().beginTransaction().replace(R.id.container, fragmentCompare).commit();
                return true;
            case R.id.ToProfile:
                getFragmentManager().beginTransaction().replace(R.id.container, fragmentProfile).commit();
                return true;
        }
        return false;
    }
}
