package com.example.monitoringrisks;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Diagram.class},version = 1,exportSchema = false)
public abstract class TableDiagram extends RoomDatabase {
    private static Builder instance;

    public TableDiagram() {
    }
    public static Builder getInstance(Context context, String namedatabase) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, TableDiagram.class, namedatabase);

        }
        return instance;
    }

    public abstract DaoDiagram DaoDiagram();
}
