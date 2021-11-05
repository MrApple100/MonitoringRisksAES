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
    @Query("SELECT * FROM AESS")
    public List<AES> findAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void add(List<AES> aesList);
    @Update
    public void update(AES aes);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(AES aes);
    @Query("SELECT * FROM AESS WHERE id =:hcName")
    public LiveData<AES> getbyName(Integer hcName);
    @Delete(entity = AES.class)
    public void deletebyid(Integer id);
}
