package com.example.monitoringrisks;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class DiagramNetwork {

        private Retrofit retrofit;
        private DiagramAPI api;

        public DiagramNetwork() {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            this.api = retrofit.create(DiagramAPI.class);
        }

        public List<Diagram> getAll() {
            Response response = null;
            try {
                response =  api.getAll().execute();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                return null;
            }

            return (List<Diagram>) response.body();
        }
        public List<Diagram> getDiagrams(int id) {
            Response response = null;
            try {
                response =  api.getDiagrams(id).execute();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                return null;
            }

            return (List<Diagram>) response.body();
        }
    }