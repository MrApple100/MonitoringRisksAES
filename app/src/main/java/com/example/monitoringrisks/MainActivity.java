package com.example.monitoringrisks;

import android.app.Activity;
import android.content.Context;
import android.database.CursorWindow;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.monitoringrisks.Fragments.FragmentManyAES;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private static Context instance;
    private static Activity activity;



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

        HashMap<String,Float> data = new HashMap<>();
        for(int i=0;i<6;i++){
            data.put((i+1)+"",i+1f);
        }
        Diagram diagramP = new Diagram("Pie", TypeDiagram.Pie,data,1);
        Diagram diagramR = new Diagram("Radar", TypeDiagram.Radar,data,2);
        Diagram diagramB = new Diagram("Bar", TypeDiagram.Bar,data,3);
        List<Diagram> diagramHashMap = new ArrayList<>();
        diagramHashMap.add(diagramP);
        diagramHashMap.add(diagramR);
        diagramHashMap.add(diagramB);
        System.out.println("dhm"+diagramHashMap);
        AES aes = new AES("AES2");
        aes.setDiscription("хорошая АЭС");
        aes.setList_diagram(diagramHashMap);
        StaticTables.getInstance().daoAES.insert(aes);
        System.out.println(StaticTables.getInstance().daoAES.findAll());
        AESRepository.getInstance().refresh();
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft= fm.beginTransaction();
        FragmentManyAES fragmentManyAES = new FragmentManyAES(Arrays.asList(new AES[]{aes}));


        ft.replace(R.id.ActivityFrame,fragmentManyAES);

        ft.commit();







    }

    public static Context getInstance() {
        return instance;
    }
    public static Activity getActivity() {
        return activity;
    }
}