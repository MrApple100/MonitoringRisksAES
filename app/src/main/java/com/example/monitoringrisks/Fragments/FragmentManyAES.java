package com.example.monitoringrisks.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.monitoringrisks.AES;
import com.example.monitoringrisks.AESRepository;
import com.example.monitoringrisks.AdapterManyAES;
import com.example.monitoringrisks.R;
import com.example.monitoringrisks.databinding.ViewFragmentManyAesBinding;
import com.example.monitoringrisks.viewmodel.AESViewModel;
import com.example.monitoringrisks.viewmodel.ManyAESViewModel;

import java.util.List;
import java.util.stream.Collectors;

public class FragmentManyAES extends Fragment {
    List<AES> aesList;
    ManyAESViewModel manyAESViewModel;
    ViewFragmentManyAesBinding binding;
    ViewModelProvider mFragmentProvider;

    public FragmentManyAES(List<AES> aesList) {
        this.aesList = aesList;



    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mFragmentProvider == null){
            mFragmentProvider = new ViewModelProvider(this);
        }
        manyAESViewModel = mFragmentProvider.get(ManyAESViewModel.class);
        Log.d("AES", AESRepository.getInstance().getData()+"");
        List<AESViewModel> aesViewModels = AESRepository.getInstance().getData().stream().map(i -> {
            AESViewModel aesViewModel = new AESViewModel();
            aesViewModel.aesLiveData.set(i);
            return aesViewModel;}).collect(Collectors.toList());
        Log.d("AES",aesViewModels+"");

        manyAESViewModel.listaes.setValue(aesViewModels);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.view_fragment_many_aes,container,false);
        if(AESRepository.getInstance().getData().size()==0){
            AESRepository.getInstance().refresh();
        }
        binding.setVm(manyAESViewModel);
        AdapterManyAES adapterManyAES = new AdapterManyAES(manyAESViewModel);
        binding.setAdapter(adapterManyAES);
        //binding.executePendingBindings();
        binding.getRoot().setBackgroundColor(Color.BLUE);
        //RecyclerView recyclerView = binding.getRoot().findViewById(R.id.RVaes);
        //recyclerView.setAdapter(adapterManyAES);

        return binding.getRoot();
    }
}
