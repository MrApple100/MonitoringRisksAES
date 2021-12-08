package com.example.monitoringrisks;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.monitoringrisks.Fragments.DaoFavoriteAES;

@Database(entities = {AES.class},version = 1,exportSchema = false)
public abstract class TableAES extends RoomDatabase {
    private static Builder instance;

    public TableAES() {
    }
    public static Builder getInstance(Context context, String namedatabase) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, TableAES.class, namedatabase);

        }
        return instance;
    }

    public abstract DaoAES DaoAES();
    public abstract DaoFavoriteAES DaoFavoriteAES();
}
