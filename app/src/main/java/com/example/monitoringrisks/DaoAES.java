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
interface DaoAES {
    @Query("SELECT * FROM AESS")
    List<AES> findAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(List<AES> aesList);
    @Update
    void update(AES aes);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AES aes);
    @Query("SELECT * FROM AESS WHERE id =:hcName")
    LiveData<AES> getbyName(Integer hcName);
    @Delete(entity = AES.class)
    void deletebyid(Integer id);
}
