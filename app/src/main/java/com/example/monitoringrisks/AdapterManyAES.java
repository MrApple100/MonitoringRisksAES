package com.example.monitoringrisks;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitoringrisks.databinding.VManyAesItemBinding;
import com.example.monitoringrisks.viewmodel.AESViewModel;
import com.example.monitoringrisks.viewmodel.ManyAESViewModel;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdapterManyAES extends RecyclerView.Adapter<AdapterManyAES.AESHolder> {

    ManyAESViewModel manyAESViewModel;
    VManyAesItemBinding binding;


    public AdapterManyAES(ManyAESViewModel manyAESViewModel) {
            this.manyAESViewModel = manyAESViewModel;
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
        if(aes.getIs_favorite())
            setfavorite.setBackgroundColor(Color.RED);
        setfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(aes.getIs_favorite()){
                    aes.setIs_favorite(false);
                }else{
                    aes.setIs_favorite(true);
                    setfavorite.setBackgroundColor(Color.RED);
                }



                System.out.println(new StaticTables().daoAES.findAll());
                new StaticTables().daoAES.changeisFav(aes.getIs_favorite(),aes.getId());

                List<AES> aess = new StaticTables().daoAES.findAll().stream().filter(new Predicate<AES>() {
                    @Override
                    public boolean test(AES aes) {
                        return aes.getIs_favorite();
                    }
                }).collect(Collectors.toList());
                System.out.println("1 "+aess);

                aess = new StaticTables().daoAES.findAll().stream().filter(new Predicate<AES>() {
                    @Override
                    public boolean test(AES aes) {
                        return aes.getIs_favorite();
                    }
                }).collect(Collectors.toList());
                System.out.println("2 "+aess);
                AESRepository.getInstance().refresh();

                aess = new StaticTables().daoAES.findAll().stream().filter(new Predicate<AES>() {
                    @Override
                    public boolean test(AES aes) {
                        return aes.getIs_favorite();
                    }
                }).collect(Collectors.toList());
                System.out.println("3 "+aess);

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
