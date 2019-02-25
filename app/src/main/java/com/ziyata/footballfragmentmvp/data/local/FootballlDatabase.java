package com.ziyata.footballfragmentmvp.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ziyata.footballfragmentmvp.model.TeamsItem;

@Database(entities = TeamsItem.class, version = 1)
public abstract class FootballlDatabase extends RoomDatabase{

    public abstract FootballDao footballDao();
    public static FootballlDatabase footballlDatabase;
    public static FootballlDatabase getFootballlDatabase(Context context){
        synchronized (FootballlDatabase.class){
            if (footballlDatabase == null){
                footballlDatabase = Room.databaseBuilder(context, FootballlDatabase.class, "db_football")
                        .allowMainThreadQueries().build();
            }
        }return footballlDatabase;
    }

}