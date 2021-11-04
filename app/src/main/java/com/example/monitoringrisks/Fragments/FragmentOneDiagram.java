package com.example.monitoringrisks.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.monitoringrisks.Diagram;
import com.example.monitoringrisks.R;
import com.github.mikephil.charting.charts.Chart;

public class FragmentOneDiagram extends Fragment {

    Diagram diagram;

    public FragmentOneDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_fragment_one_diagram, container, false);
        RelativeLayout OneRL = (RelativeLayout) view.findViewById(R.id.OneRL);

        Chart chart = diagram.createChart();

        OneRL.addView(chart,0,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
        return view;
    }

}
