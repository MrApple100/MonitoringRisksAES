package com.example.monitoringrisks.Converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ConverterHashmapStringFloat {
    @TypeConverter
    public String fromHashMap(HashMap<String,Float> inputdata){
        List<String> strflt = new ArrayList<>(inputdata.keySet());
        Gson gson = new Gson();
        return gson.toJson(strflt.stream().map(i -> (String)i+"/"+inputdata.get((String)i)).collect(Collectors.toList()));
    }
    @TypeConverter
    public HashMap<String,Float> toHashMap(String str){
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> list =gson.fromJson(str,type);
        HashMap<String,Float> hashMap = new HashMap<>(list.size());
        list.stream().forEach(i ->{
            String[] kv = i.split("/");
            hashMap.put(kv[0],Float.valueOf(kv[1])); return;});
        return hashMap;
    }
}
