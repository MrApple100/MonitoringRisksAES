package com.example.monitoringrisks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AESAPI {
    @POST("/AESS")
    public Call<Boolean>  postAllAES(@Body List<AES> aeses);
    @POST("/AESS/{Name}")
    public Call<Boolean> postAES(@Path("Name")int aesid, @Body AES aes);
    @GET("/AESS")
    public Call<List<AES>> getAllAES();
    @GET("/AESS/{Name}")
    public Call<List<AES>> getAES(@Path("Name") String Name);
}
