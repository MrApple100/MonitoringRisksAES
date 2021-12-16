package com.example.monitoringrisks.Fragments.Pages;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.example.monitoringrisks.Fragments.FragmentManyAES;
import com.example.monitoringrisks.R;
import com.example.monitoringrisks.StaticTables;
import com.example.monitoringrisks.TypeDiagram;
import com.example.monitoringrisks.viewmodel.AESViewModel;
import com.example.monitoringrisks.viewmodel.EnumFragmentName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FragmentFavorite extends Fragment {
    private final EnumFragmentName enumFragmentName = EnumFragmentName.Favorite;
    static private FragmentFavorite instance;
    public static final String instancename = "FAVORITE";
    private static FragmentManyAES activefragmentManyAES;

    public static FragmentFavorite getInstance() {
        if(instance==null){
            instance = new FragmentFavorite();
        }else{
            FragmentManager fm = instance.getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            List<AESViewModel> aesViewModels = StaticTables.getInstance().daoFavoriteAES.findFavoriteAll().stream().map(i -> {
                AESViewModel aesViewModel = new AESViewModel();
                aesViewModel.aesLiveData.set(i);
                return aesViewModel;
            }).collect(Collectors.toList());
            Log.d("FAVLIST",aesViewModels.toString());
            FragmentManyAES fragmentManyAES = new FragmentManyAES(aesViewModels,instance.enumFragmentName,instancename,instance);
           // NavigationFragment.getInstance().loadFragment(instance,fragmentManyAES);
            ft.replace(R.id.FLfavorite,fragmentManyAES);
            ft.commit();
        }
        return instance;
    }
    private FragmentFavorite(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_view,container,false);

        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("ATTACH","ATTACHFAVORITE");

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();

        List<AESViewModel> aesViewModels = StaticTables.getInstance().daoFavoriteAES.findFavoriteAll().stream().map(i -> {
            AESViewModel aesViewModel = new AESViewModel();
            aesViewModel.aesLiveData.set(i);
            return aesViewModel;
        }).collect(Collectors.toList());
        Log.d("FAVLIST",aesViewModels.toString());

        FragmentManyAES fragmentManyAES = new FragmentManyAES(aesViewModels,enumFragmentName,instancename,instance);

        ft.replace(R.id.FLfavorite,fragmentManyAES);
        ft.commit();

    }
}
