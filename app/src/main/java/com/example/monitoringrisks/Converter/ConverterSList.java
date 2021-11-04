package com.example.monitoringrisks.Converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ConverterSList {
    @TypeConverter
    public String from(List list){
        Gson gson = new Gson();
        return gson.toJson(list);
    }
    @TypeConverter
    public List to(String str){
        Gson gson = new Gson();
        Type type = new TypeToken<List>() {
        }.getType();
        return gson.fromJson(str,type);
    }
}
