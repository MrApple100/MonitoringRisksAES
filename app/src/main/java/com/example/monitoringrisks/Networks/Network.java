package com.example.monitoringrisks.Networks;

import android.util.Log;

import com.example.monitoringrisks.AES;
import com.example.monitoringrisks.AESAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    Retrofit retrofit;
    AESAPI aesapi;
    public Network(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.155:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.aesapi = retrofit.create(AESAPI.class);
    }
    public void postAES(int idaes, AES aes) {
        Log.d("postAES", aes.toString());
        aesapi.postAES(idaes, aes).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("postAES", response.body() + "");

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("postAES", t + "");

            }
        });

    }
}
