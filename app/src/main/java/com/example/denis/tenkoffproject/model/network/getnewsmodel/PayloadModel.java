package com.example.denis.tenkoffproject.model.network.getnewsmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denis on 09.06.2017.
 */

public class PayloadModel {
    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("payload")
    @Expose
    private List<NewsModel> payload = null;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public List<NewsModel> getPayload() {
        return payload;
    }

    public void setPayload(List<NewsModel> payload) {
        this.payload = payload;
    }
}
