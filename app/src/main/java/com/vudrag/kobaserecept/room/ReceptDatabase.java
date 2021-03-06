package com.vudrag.kobaserecept.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.vudrag.kobaserecept.classes.ReceptInfo;
import com.vudrag.kobaserecept.classes.Sastojak;

@Database(entities = {ReceptInfo.class, Sastojak.class}, exportSchema = false, version = 5)
public abstract class ReceptDatabase extends RoomDatabase {

    private static ReceptDatabase INSTANCE;

    public static synchronized ReceptDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,
                    ReceptDatabase.class, "recept_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public abstract Dao dao();
}
