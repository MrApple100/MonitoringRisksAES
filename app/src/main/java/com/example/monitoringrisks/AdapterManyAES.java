package com.example.monitoringrisks;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.monitoringrisks.Activities.ActivityAES;
import com.example.monitoringrisks.databinding.VManyAesItemBinding;
import com.example.monitoringrisks.viewmodel.AESViewModel;
import com.example.monitoringrisks.viewmodel.EnumFragmentName;
import com.example.monitoringrisks.viewmodel.ManyAESViewModel;
import com.google.gson.Gson;

public class AdapterManyAES extends RecyclerView.Adapter<AdapterManyAES.AESHolder> {

    ManyAESViewModel manyAESViewModel;
    VManyAesItemBinding binding;
    EnumFragmentName name;
    String rootfragmentname;
    Fragment rootfragment;

    public AdapterManyAES(ManyAESViewModel manyAESViewModel, EnumFragmentName name, String rootfragmentname, Fragment rootfragment) {
        this.manyAESViewModel = manyAESViewModel;
        this.name = name;
        this.rootfragmentname = rootfragmentname;
        this.rootfragment = rootfragment;
    }


    @NonNull
    @Override
    public AESHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.v_many_aes_item, parent, false);
        return new AESHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull AESHolder holder, int position) {
        AESViewModel aesViewModel = manyAESViewModel.listaes.getValue().get(position);
        AES aes = aesViewModel.aesLiveData.get();
        ImageView setfavorite = binding.getRoot().findViewById(R.id.SETFavorite);
        LinearLayout buttofragAES = binding.getRoot().findViewById(R.id.ButtontofragAES);
        if (aes.getIs_favorite()) {
            setfavorite.setBackgroundColor(Color.BLUE);
        } else {
            setfavorite.setBackgroundColor(Color.RED);
        }

        setfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aes.setIs_favorite(!aes.getIs_favorite());
                if (aes.getIs_favorite()) {
                    setfavorite.setBackgroundColor(Color.BLUE);
                } else {
                    setfavorite.setBackgroundColor(Color.RED);
                }
                if (name == EnumFragmentName.Favorite && !aes.getIs_favorite()) {
                    holder.itemView.setVisibility(View.GONE);
                    System.out.println("REMOVE" + manyAESViewModel.listaes.getValue());
                }

                StaticTables.getInstance().daoFavoriteAES.changeisFav(aes.getIs_favorite(), aes.getId());

                System.out.println(StaticTables.getInstance().daoAES.findAll());
            }
        });
        buttofragAES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFF"+aes.getId());
                Intent intent = new Intent(MainActivity.getActivity(), ActivityAES.class);
                intent.putExtra("aesid",aes.getId());
                MainActivity.getActivity().startActivity(intent);
                //NavigationFragment.getInstance().toAESPage(rootfragment,aes);

            }
        });
        Log.d("AES", aesViewModel.aesLiveData.get().getName());
        holder.bind(aesViewModel);
    }

    @Override
    public int getItemCount() {
        return manyAESViewModel.listaes.getValue().size();
    }


    public static class AESHolder extends RecyclerView.ViewHolder {
        private VManyAesItemBinding binding;

        AESHolder(VManyAesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(AESViewModel aesViewModel) {

            binding.setAes(aesViewModel);
            binding.executePendingBindings();
        }

        public void unbind() {
            binding.unbind();
            binding.executePendingBindings();
        }
    }


}
