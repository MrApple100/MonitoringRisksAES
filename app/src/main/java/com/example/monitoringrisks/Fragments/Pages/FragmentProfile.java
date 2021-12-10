package com.example.monitoringrisks.Fragments.Pages;

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

import com.example.monitoringrisks.R;
import com.example.monitoringrisks.StaticTables;
import com.example.monitoringrisks.databinding.FragmentProfileViewBinding;
import com.example.monitoringrisks.viewmodel.EnumFragmentName;
import com.example.monitoringrisks.viewmodel.ProfileViewModel;

public class FragmentProfile extends Fragment {
    private final EnumFragmentName enumFragmentName = EnumFragmentName.Profile;
    static private FragmentProfile instance;
    ViewModelProvider mFragmentProvider;
    ProfileViewModel profileViewModel;

    public static FragmentProfile getInstance() {
        if(instance==null){
            instance = new FragmentProfile();
        }
        return instance;
    }
    private FragmentProfile(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mFragmentProvider == null){
            mFragmentProvider = new ViewModelProvider(this);
        }
        profileViewModel = mFragmentProvider.get(ProfileViewModel.class);
        Log.d("USER",StaticTables.getInstance().daoUser.get().getSurname()+"");
        profileViewModel.user.setValue(StaticTables.getInstance().daoUser.get());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       FragmentProfileViewBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_view,container,false);
       binding.setVm(profileViewModel);

       return binding.getRoot();
    }
}
