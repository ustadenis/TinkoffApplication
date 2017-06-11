package com.example.denis.tenkoffproject.model.storage.news;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Denis on 10.06.2017.
 */

@Entity(tableName = "news")
public class NewsEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "message")
    private String mMessage;

    @ColumnInfo(name = "content")
    private String mContent;

    @ColumnInfo(name = "date")
    private Long mDate;

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public Long getDate() {
        return mDate;
    }

    public void setDate(Long mDate) {
        this.mDate = mDate;
    }
}
