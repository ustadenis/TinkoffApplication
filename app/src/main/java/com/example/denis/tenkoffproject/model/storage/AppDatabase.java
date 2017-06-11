package com.example.denis.tenkoffproject.model.storage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.denis.tenkoffproject.model.storage.news.NewsDao;
import com.example.denis.tenkoffproject.model.storage.news.NewsEntity;

/**
 * Created by Denis on 10.06.2017.
 */

@Database(entities = {NewsEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsModel();
}
