package com.example.monitoringrisks.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.monitoringrisks.AES;
import com.example.monitoringrisks.AESRepository;
import com.example.monitoringrisks.Fragments.FragmentBackButton;
import com.example.monitoringrisks.Fragments.FragmentManyDiagrams;
import com.example.monitoringrisks.R;
import com.example.monitoringrisks.StaticTables;
import com.example.monitoringrisks.databinding.ViewFragmentAesBinding;
import com.example.monitoringrisks.viewmodel.AESViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.stream.Collectors;

public class ActivityAES extends FragmentActivity {
    private AESViewModel aesViewModel;
    ViewFragmentAesBinding binding;
    ViewModelProvider mFragmentProvider;
    static ActivityAES instance;

    int hcName;

    public static ActivityAES getInstance() {
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_fragment_aes);
        instance = this;
        Intent intent = getIntent();

        hcName = intent.getIntExtra("aesid",0);
        System.out.println("HHHHHHHHHHHHHHHHH"+hcName);
        if(mFragmentProvider == null){
            mFragmentProvider = new ViewModelProvider(this);
        }
        aesViewModel = mFragmentProvider.get(AESViewModel.class);
        aesViewModel.aesLiveData.set(AESRepository.getInstance().getAES(hcName));

        binding = DataBindingUtil.setContentView(this, R.layout.view_fragment_aes);
        binding.setAes(aesViewModel);



        AES aes = aesViewModel.aesLiveData.get();

        ImageView setfavorite = binding.getRoot().findViewById(R.id.SETFavorite);
        //ImageView back =(ImageView) binding.getRoot().findViewById(R.id.tofeedorfavorite);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.Framebackbut,new FragmentBackButton());


        if(aes.getIs_favorite()){
            setfavorite.setBackgroundColor(Color.BLUE);
        }else{
            setfavorite.setBackgroundColor(Color.RED);
        }

        setfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aes.setIs_favorite(!aes.getIs_favorite());
                if(aes.getIs_favorite()){
                    setfavorite.setBackgroundColor(Color.BLUE);
                }else{
                    setfavorite.setBackgroundColor(Color.RED);
                }


                StaticTables.getInstance().daoFavoriteAES.changeisFav(aes.getIs_favorite(),aes.getId());

                System.out.println(StaticTables.getInstance().daoAES.findAll());
            }
        });

        FragmentManyDiagrams fragmentManyDiagrams = new FragmentManyDiagrams(aesViewModel.aesLiveData.get().getList_diagram());


        ft.add(R.id.AESFrame,fragmentManyDiagrams);
        ft.commitAllowingStateLoss();
    }


    @BindingAdapter("imagebytes")
    public static void loadImage(ImageView imageView, String photostr){
            Type type = new TypeToken<byte[]>() {
            }.getType();
            byte[] photo = new Gson().fromJson(photostr, type);

            Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);

        imageView.setImageBitmap(bitmap);

    }
    public void Returnback(String rootfragmentname) {
        FragmentManager fm = getSupportFragmentManager();
        //FragmentTransaction ft = fm.beginTransaction();
        System.out.println(fm.getBackStackEntryCount());
        System.out.println(fm.getFragments().stream().map(i -> i.getClass().getName()
        ).collect(Collectors.toList()));
        /*
        Fragment fragment = null;
        if (rootfragmentname.equals("FEED")) {
            fragment = FragmentFeed.getInstance();
        } else if (rootfragmentname.equals("FAVORITE")) {
            fragment = FragmentFavorite.getInstance();
        }

         */


        fm.popBackStack();


    }

}
