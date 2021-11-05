package com.example.monitoringrisks.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ManyAESViewModel extends ViewModel {
    public MutableLiveData<List<AESViewModel>> listaes = new MutableLiveData<>();


}
