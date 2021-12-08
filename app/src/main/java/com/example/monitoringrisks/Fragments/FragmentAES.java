package com.example.monitoringrisks.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.monitoringrisks.AESRepository;
import com.example.monitoringrisks.R;
import com.example.monitoringrisks.databinding.ViewFragmentAesBinding;
import com.example.monitoringrisks.viewmodel.AESViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class FragmentAES extends Fragment {
    private AESViewModel aesViewModel;
    ViewFragmentAesBinding binding;
    ViewModelProvider mFragmentProvider;

    public FragmentAES(int hcName) {
        if(mFragmentProvider == null){
            mFragmentProvider = new ViewModelProvider(this);
        }
        aesViewModel = mFragmentProvider.get(AESViewModel.class);
        aesViewModel.aesLiveData.set(AESRepository.getInstance().getAES(hcName));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       //View view = inflater.inflate(R.layout.view_fragment_aes,container,false);
        binding = DataBindingUtil.setContentView(this.getActivity(), R.layout.view_fragment_aes);


        binding.setAes(aesViewModel);

        FragmentManyDiagrams fragmentManyDiagrams = new FragmentManyDiagrams(aesViewModel.aesLiveData.get().getList_diagram());
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.add(R.id.AESFrame,fragmentManyDiagrams);
        ft.commitAllowingStateLoss();
        return null;
    }

    @BindingAdapter("imagebytes")
    public static void loadImage(ImageView imageView, String photostr){
            Type type = new TypeToken<byte[]>() {
            }.getType();
            byte[] photo = new Gson().fromJson(photostr, type);

            Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);

        imageView.setImageBitmap(bitmap);

    }
}
