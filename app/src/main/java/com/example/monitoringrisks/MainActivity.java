package com.example.monitoringrisks;

import android.app.Activity;
import android.content.Context;
import android.database.CursorWindow;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.monitoringrisks.Fragments.Pages.FragmentFeed;
import com.example.monitoringrisks.Fragments.FragmentNavigationPanel;
import com.example.monitoringrisks.Fragments.Pages.NavigationFragment;

import java.lang.reflect.Field;

public class MainActivity extends FragmentActivity {
    private static Context instance;
    private static Activity activity;
    private static FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //чтобы на вывод в консоль было выделено больше памяти
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024);//100mb
        } catch (Exception e) {

        }

        instance =this;
        activity = this;


        fm = getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();


        FragmentNavigationPanel navigationPanel = FragmentNavigationPanel.getInstance();
        ft.add(R.id.BotNav,navigationPanel);
        ft.commitAllowingStateLoss();
        ft = fm.beginTransaction();
        FragmentFeed fragmentFeed = FragmentFeed.getInstance();

        ft.replace(R.id.ActivityFrame,fragmentFeed);
        ft.commitAllowingStateLoss();



        User user = new User("alexander","zaporozhskih");

        StaticTables.getInstance().daoUser.insert(user);


    }

    public static Context getInstance() {
        return instance;
    }
    public static Activity getActivity() {
        return activity;
    }
    public static FragmentManager getFm() {
        return fm;
    }
}