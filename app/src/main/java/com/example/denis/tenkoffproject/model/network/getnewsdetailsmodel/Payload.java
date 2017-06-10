package com.example.denis.tenkoffproject.model.network.getnewsdetailsmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;

public class Payload {

    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("creationDate")
    @Expose
    private CreationDate creationDate;
    @SerializedName("lastModificationDate")
    @Expose
    private LastModificationDate lastModificationDate;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("bankInfoTypeId")
    @Expose
    private Integer bankInfoTypeId;
    @SerializedName("typeId")
    @Expose
    private String typeId;

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getCreationDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        date.setTime(creationDate.getMilliseconds());
        return date.toString();
    }

    public void setCreationDate(CreationDate creationDate) {
        this.creationDate = creationDate;
    }

    public LastModificationDate getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(LastModificationDate lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBankInfoTypeId() {
        return bankInfoTypeId;
    }

    public void setBankInfoTypeId(Integer bankInfoTypeId) {
        this.bankInfoTypeId = bankInfoTypeId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

}
