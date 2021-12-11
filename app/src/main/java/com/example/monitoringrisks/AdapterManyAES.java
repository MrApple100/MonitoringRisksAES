package com.example.monitoringrisks;

import android.content.Context;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitoringrisks.Fragments.FragmentAES;
import com.example.monitoringrisks.Fragments.Pages.FragmentFeed;
import com.example.monitoringrisks.databinding.VManyAesItemBinding;
import com.example.monitoringrisks.viewmodel.AESViewModel;
import com.example.monitoringrisks.viewmodel.EnumFragmentName;
import com.example.monitoringrisks.viewmodel.ManyAESViewModel;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdapterManyAES extends RecyclerView.Adapter<AdapterManyAES.AESHolder> {

    ManyAESViewModel manyAESViewModel;
    VManyAesItemBinding binding;
    EnumFragmentName name;
    Fragment rootfragment;

    public AdapterManyAES(ManyAESViewModel manyAESViewModel, EnumFragmentName name, Fragment rootfragment) {
            this.manyAESViewModel = manyAESViewModel;
            this.name = name;
            this.rootfragment = rootfragment;
    }


    @NonNull
    @Override
    public AESHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.v_many_aes_item,parent,false);
        return new AESHolder(binding);
    }





    @Override
    public void onBindViewHolder(@NonNull AESHolder holder, int position) {
        AESViewModel aesViewModel = manyAESViewModel.listaes.getValue().get(position);
        AES aes = aesViewModel.aesLiveData.get();
        ImageView setfavorite = binding.getRoot().findViewById(R.id.SETFavorite);
        LinearLayout buttofragAES =binding.getRoot().findViewById(R.id.ButtontofragAES);
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
                if(name==EnumFragmentName.Favorite && !aes.getIs_favorite()){
                    holder.itemView.setVisibility(View.GONE);
                    System.out.println("REMOVE"+manyAESViewModel.listaes.getValue());
                }

                StaticTables.getInstance().daoFavoriteAES.changeisFav(aes.getIs_favorite(),aes.getId());

                System.out.println(StaticTables.getInstance().daoAES.findAll());
            }
        });
        buttofragAES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = rootfragment.getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FragmentAES fragmentAES = new FragmentAES(aes.getId());

                ft.replace(R.id.ActivityFrame,fragmentAES);
                ft.commit();
                ft.addToBackStack("tofeedorfavorite");
            }
        });
        Log.d("AES",aesViewModel.aesLiveData.get().getName());
        holder.bind(aesViewModel);
    }

    @Override
    public int getItemCount() {
        return manyAESViewModel.listaes.getValue().size();
    }


    public static class AESHolder extends RecyclerView.ViewHolder {
        private  VManyAesItemBinding binding;

        AESHolder(VManyAesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(AESViewModel aesViewModel) {

            binding.setAes(aesViewModel);
            binding.executePendingBindings();
        }
        public void unbind(){
            binding.unbind();
            binding.executePendingBindings();
        }
    }




    /*
    с применением имплементейшион куна
   public AdapterManyAES(Context context) {
       super(context, R.layout.v_many_aes_item, DiffUtils.getInstance().getAesItemCallback());

   }

    @Override
    protected void onBindItem(VManyAesItemBinding binding, AESViewModel item, RecyclerView.ViewHolder holder) {
        binding.setAes(item);
    }

     */
}
