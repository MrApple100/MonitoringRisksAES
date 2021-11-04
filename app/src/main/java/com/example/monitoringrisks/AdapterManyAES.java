package com.example.monitoringrisks;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitoringrisks.databinding.VManyAesItemBinding;

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
