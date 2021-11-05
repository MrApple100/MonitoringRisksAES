package com.example.monitoringrisks;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;


@Entity(tableName = "User")
public class User {
    @PrimaryKey
    private int id = 0;
    private String name;
    private String surname;
    @TypeConverters(ConverterPosition.class)
    private Enum<Position> pos;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.pos = Position.User;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Enum<Position> getPos() {
        return pos;
    }

    public void setPos(Enum<Position> pos) {
        this.pos = pos;
    }
}
