package com.example.monitoringrisks;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
interface DaoDiagram {
    @Query("SELECT * FROM diagrams")
    List<Diagram> findAll();
    @Query("SELECT * FROM diagrams WHERE idAES= :idAES")
    List<Diagram> getdiagramsbyidAES(int idAES);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Diagram diagram);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(List<Diagram> diagramList);
    @Update
    void update(List<Diagram> diagramList);
    @Update
    void update(Diagram diagram);
    @Query("DELETE FROM `diagrams` WHERE idAES= :idAES")
    void deleteByidAES(Integer idAES);
}
