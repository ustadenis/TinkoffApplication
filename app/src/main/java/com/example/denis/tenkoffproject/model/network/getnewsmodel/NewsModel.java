package com.example.denis.tenkoffproject.model.network.getnewsmodel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;

public class NewsModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("publicationDate")
    @Expose
    private PublicationDateModel publicationDate;
    @SerializedName("bankInfoTypeId")
    @Expose
    private Integer bankInfoTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPublicationDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        date.setTime(publicationDate.getMilliseconds());
        return date.toString();
    }

    public long getPublicationDateInMS() {
        return publicationDate.getMilliseconds();
    }

    public void setPublicationDate(PublicationDateModel publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getBankInfoTypeId() {
        return bankInfoTypeId;
    }

    public void setBankInfoTypeId(Integer bankInfoTypeId) {
        this.bankInfoTypeId = bankInfoTypeId;
    }

}