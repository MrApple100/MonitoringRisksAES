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
public interface DaoUser {
    @Update(onConflict = OnConflictStrategy.REPLACE)
     void update(User user);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);
    @Query("SELECT * FROM User WHERE id = 0")
     User get();
    @Delete(entity = User.class)
     void delete(Integer id);
}
