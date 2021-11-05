package com.example.monitoringrisks;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ConverterPosition {
    @TypeConverter
    public String fromHashMap(Enum<Position> inputdata){
        return inputdata.name();
    }
    @TypeConverter
    public Enum<Position> toHashMap(String str){
        return Position.valueOf(str);
    }
}
