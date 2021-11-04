package com.example.monitoringrisks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface AESAPI {
    @GET("AESS")
    public Call<List<AES>> getAllAES();
    @GET("AESS/{Name}")
    public Call<List<AES>> getAES(@Path("Name") String Name);
}
