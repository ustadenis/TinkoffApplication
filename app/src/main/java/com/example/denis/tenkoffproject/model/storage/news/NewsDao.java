package com.example.denis.tenkoffproject.model.storage.news;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Denis on 10.06.2017.
 */

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news ORDER BY date DESC")
    Flowable<List<NewsEntity>> getNews();

    @Query("DELETE FROM news")
    int deleteNews();

    @Insert(onConflict = REPLACE)
    void insertNews(List<NewsEntity> news);
}
