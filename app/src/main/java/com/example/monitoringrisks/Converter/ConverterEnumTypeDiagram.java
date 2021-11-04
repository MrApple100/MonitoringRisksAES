package com.example.monitoringrisks.Converter;

import androidx.room.TypeConverter;

import com.example.monitoringrisks.TypeDiagram;

public class ConverterEnumTypeDiagram {
    @TypeConverter
    public String fromTypeDiagram(TypeDiagram typeDiagram){
        return typeDiagram.name();
    }
    @TypeConverter
    public TypeDiagram toTypeDiagram(String typeDiagram){
        return TypeDiagram.valueOf(typeDiagram);
    }
}
