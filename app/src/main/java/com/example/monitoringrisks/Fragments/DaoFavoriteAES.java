package com.example.monitoringrisks.Fragments;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.monitoringrisks.AES;

import java.util.List;

@Dao
public interface DaoFavoriteAES {
    @Query("SELECT * FROM AESeS WHERE isFavorite=1")
    public List<AES> findFavoriteAll();
    @Query("UPDATE AESeS SET isFavorite=:isfav WHERE id=:id")
    public int changeisFav(boolean isfav,Integer id);



}

