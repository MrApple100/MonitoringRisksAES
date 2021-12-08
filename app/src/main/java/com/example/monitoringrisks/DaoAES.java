package com.example.monitoringrisks;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoAES {
    @Query("SELECT * FROM AESeS")
    public List<AES> findAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void add(List<AES> aesList);
    @Update(onConflict = OnConflictStrategy.REPLACE,entity = AES.class)
    public void update(AES aes);
    @Insert(onConflict = OnConflictStrategy.REPLACE,entity = AES.class)
    public void insert(AES aes);
    @Query("SELECT * FROM AESeS WHERE id =:hcName")
    public AES getbyName(Integer hcName);
    @Delete
    public void delete(AES aes);


    @Query("SELECT * FROM AESeS WHERE isFavorite=1")
    public List<AES> findFavoriteAll();
    @Query("UPDATE AESeS SET isFavorite=:isfav WHERE id=:id")
    public int changeisFav(boolean isfav,Integer id);
}
