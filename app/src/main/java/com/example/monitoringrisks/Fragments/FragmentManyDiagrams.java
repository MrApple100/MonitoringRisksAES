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

import com.example.monitoringrisks.Diagram;
import com.example.monitoringrisks.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentManyDiagrams extends Fragment {
    private List<Diagram> list_diagram = new ArrayList<>();
    public FragmentManyDiagrams( List<Diagram> list_diagram) {
        this.list_diagram = list_diagram;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_fragment_many_diagrams,container,false);
        System.out.println("ku");
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        //System.out.println(list_diagram);
        for(Diagram diagram:list_diagram) {
            //System.out.println(diagram.getInputdata().size());
            FragmentOneDiagram chart = new FragmentOneDiagram(diagram);
            ft.add(R.id.MainFrame, chart);
        }
        ft.commitAllowingStateLoss();
        return view;
    }
}
