package com.example.monitoringrisks.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.monitoringrisks.AES;
import com.example.monitoringrisks.AESRepository;
import com.example.monitoringrisks.Diagram;
import com.example.monitoringrisks.R;
import com.example.monitoringrisks.StaticTables;
import com.example.monitoringrisks.TypeDiagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FragmentFeed extends Fragment {
    private static FragmentFeed instance;

    public static FragmentFeed getInstance() {
        if(instance==null){
            instance = new FragmentFeed();
        }
        return instance;
    }
    private FragmentFeed(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_view,container,false);
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
        AES aes = new AES("AES3");
        aes.setDiscription("хорошая АЭС");
        aes.setList_diagram(diagramHashMap);
        StaticTables.getInstance().daoAES.insert(aes);
        System.out.println(StaticTables.getInstance().daoAES.findAll());
        AESRepository.getInstance().refresh();
        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft= fm.beginTransaction();
        FragmentManyAES fragmentManyAES = new FragmentManyAES(Arrays.asList(new AES[]{aes}));

        ft.replace(R.id.FLfeed,fragmentManyAES);

        ft.commit();
        return view;
    }
}
