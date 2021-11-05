package com.example.monitoringrisks;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.monitoringrisks.viewmodel.AESViewModel;

public class DiffUtils {
    private DiffUtil.ItemCallback<AESViewModel> aesItemCallback;
    private static DiffUtils S_DIFF_UTILS = new DiffUtils();
    private DiffUtils() {};

    public static DiffUtils getInstance(){
        return S_DIFF_UTILS;
    }

    public DiffUtil.ItemCallback<AESViewModel> getAesItemCallback(){
        if(aesItemCallback == null){
            aesItemCallback = new DiffUtil.ItemCallback<AESViewModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull AESViewModel oldItem, @NonNull AESViewModel newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull AESViewModel oldItem, @NonNull AESViewModel newItem) {
                    return oldItem.aesLiveData.get().getId()==newItem.aesLiveData.get().getId();
                }
            };
        }
        return aesItemCallback;
    }
}
