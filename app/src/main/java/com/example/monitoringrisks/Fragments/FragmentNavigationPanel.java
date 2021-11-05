package com.example.monitoringrisks.Fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.monitoringrisks.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FragmentNavigationPanel extends Fragment {

    static BottomNavigationView navigation;

    private static FragmentNavigationPanel instance;

    public static FragmentNavigationPanel getInstance() {
        if (instance == null) {
            instance = new FragmentNavigationPanel();
        }
        return instance;
    }


    public FragmentNavigationPanel() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navigationpanel, container, false);
        navigation = (BottomNavigationView) view.findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setOnNavigationItemReselectedListener(mOnNavigationItemReselectedListener);
        Log.d("AIAIAI","OPPA");
        return view;

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ToFavorite:
                    loadFragment(FragmentFavorite.getInstance());
                    return true;
                case R.id.ToFeed:
                    loadFragment(FragmentFeed.getInstance());
                    return true;
                case R.id.ToProfile:
                    loadFragment(FragmentProfile.getInstance());
                    return true;
                case R.id.ToCompare:
                     loadFragment(FragmentFavorite.getInstance());
                    return true;
            }
            return false;
        }

    };
    private BottomNavigationView.OnNavigationItemReselectedListener mOnNavigationItemReselectedListener = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ToFeed:
                    /*
                    if (navigation.getSelectedItemId() == R.id.ToNewsFeed) {
                        if (FragmentFeed.NewsFeed.getChildAdapterPosition(FragmentFeed.NewsFeed.getChildAt(0)) != 0)
                            FragmentFeed.NewsFeed.scrollToPosition(1);
                        FragmentFeed.NewsFeed.smoothScrollToPosition(0);

                    }

                     */
            }
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.alphaup, R.anim.alphadown);

        ArrayList<Fragment> fragments = (ArrayList<Fragment>) getFragmentManager().getFragments();
        for (Fragment tempfragment : fragments) {
            if (tempfragment.getId() != FragmentNavigationPanel.getInstance().getId() && !tempfragment.isHidden())
                ft.hide(tempfragment);
        }
        ft.show(FragmentNavigationPanel.getInstance());
        if (!fragment.isHidden())
            ft.add(R.id.ActivityFrame, fragment);
        else
            ft.show(fragment);

        ft.commit();
    }

}
