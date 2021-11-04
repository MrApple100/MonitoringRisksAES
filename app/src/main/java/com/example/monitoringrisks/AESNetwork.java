package com.example.monitoringrisks;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AESNetwork {
    private Retrofit retrofit;
    private AESAPI api;

    public AESNetwork() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.155:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.api = retrofit.create(AESAPI.class);
    }

    public List<AES> getAll() {
        Response response = null;
        try {
            response =  api.getAllAES().execute();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            
        }

        return (List<AES>) response.body();
    }
    public AES getOneAES(String Name) {
        Response response = null;
        try {
            response =  api.getAES(Name).execute();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }

        return (AES) response.body();
    }
}