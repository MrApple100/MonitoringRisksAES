package com.example.monitoringrisks;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class TableUser extends RoomDatabase {
    private static Builder instance;

    public TableUser() {
    }
    public static Builder getInstance(Context context, String namedatabase) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, TableUser.class, namedatabase);

        }
        return instance;
    }

    public abstract DaoUser DaoUser();
}
