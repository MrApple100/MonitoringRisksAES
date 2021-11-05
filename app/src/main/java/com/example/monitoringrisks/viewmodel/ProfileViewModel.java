package com.example.monitoringrisks.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.monitoringrisks.AES;
import com.example.monitoringrisks.User;

import java.util.List;

public class ProfileViewModel extends ViewModel {
    public MutableLiveData<User> user = new MutableLiveData<User>();
    public MutableLiveData<List<AES>> listaes = new MutableLiveData<List<AES>>();
}
