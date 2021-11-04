package com.example.monitoringrisks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DiagramAPI {
    @GET("Diagrams")
    public Call<List<Diagram>> getAll();

    @GET("Diagrams/{id}")
    public Call<List<Diagram>> getDiagrams(@Path("id") int id);

}
