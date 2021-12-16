package com.example.monitoringrisks.Fragments.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.monitoringrisks.AES;
import com.example.monitoringrisks.Activities.ActivityAES;


import com.example.monitoringrisks.Fragments.FragmentNavigationPanel;
import com.example.monitoringrisks.MainActivity;
import com.example.monitoringrisks.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

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
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.ToFavorite:
                fragmentManager.beginTransaction().replace(R.id.ActivityFrame, fragmentFavorite).commit();// loadFragment(instance,fragmentFavorite);
                return true;

            case R.id.ToFeed:
                fragmentManager.beginTransaction().replace(R.id.ActivityFrame, fragmentFeed).commit();//loadFragment(instance,fragmentFeed);
                return true;

            case R.id.ToCompare:
                fragmentManager.beginTransaction().replace(R.id.ActivityFrame, fragmentCompare).commit();// loadFragment(instance,fragmentCompare);
                return true;
            case R.id.ToProfile:
                fragmentManager.beginTransaction().replace(R.id.ActivityFrame, fragmentProfile).commit();// loadFragment(instance,fragmentProfile);
                return true;
        }
        return false;
    }
    public void loadFragment(Fragment parentfrag,Fragment fragment) {
        FragmentTransaction ft = parentfrag.getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.alphaup, R.anim.alphadown);

        ArrayList<Fragment> fragments = (ArrayList<Fragment>) parentfrag.getFragmentManager().getFragments();
        for (Fragment tempfragment : fragments) {
            if (tempfragment.getId() != FragmentNavigationPanel.getInstance().getId() && !tempfragment.isHidden())
                ft.hide(tempfragment);
        }
        ft.show(FragmentNavigationPanel.getInstance());
        if (!fragment.isHidden())
            ft.add(R.id.ActivityFrame, fragment);
        else
            ft.attach(fragment);

        ft.commit();
    }
    public void loadFragmentwithBackStack(Fragment parentfrag,Fragment fragment,String namebackstack) {
        FragmentTransaction ft = parentfrag.getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.alphaup, R.anim.alphadown);

        ArrayList<Fragment> fragments = (ArrayList<Fragment>) parentfrag.getFragmentManager().getFragments();
        for (Fragment tempfragment : fragments) {
            if (tempfragment.getId() != FragmentNavigationPanel.getInstance().getId() && !tempfragment.isHidden())
                ft.detach(tempfragment);
        }
        ft.show(FragmentNavigationPanel.getInstance());
        if (!fragment.isHidden())
            ft.attach(fragment);
        else
            ft.show(fragment);
        ft.addToBackStack(namebackstack);
        ft.commit();

    }

    public void toAESPage(Fragment rootfragment, AES aes ){

        Intent intent = new Intent(MainActivity.getActivity(), ActivityAES.class);
        Gson gson = new Gson();
        String jsonaes = gson.toJson(aes);
        intent.putExtra("aes",jsonaes);
        startActivity(intent);
/*
        FragmentManager fm = MainActivity.getFm();
        FragmentTransaction ft = fm.beginTransaction();

                ft.detach(rootfragment);
                ft.commit();
                fm.executePendingTransactions();

                ft = fm.beginTransaction();

                 */
/*
        FragmentAES fragmentAES = new FragmentAES( aes.getId());
        FragmentBackButton.feedorfavorite = rootfragment;
        //FragmentBackButton.AEStemp = fragmentAES;
        //ft.add(R.id.AdditionalUPFrame, fragmentAES);
        ft.show(fm.findFragmentById(R.id.BotNav));

        //ft.addToBackStack(null);
        ft.commitAllowingStateLoss();


 */

        // fm.executePendingTransactions();
    }

}
