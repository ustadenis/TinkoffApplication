package com.example.denis.tenkoffproject.model.network.getnewsdetailsmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreationDate {

    @SerializedName("milliseconds")
    @Expose
    private Long milliseconds;

    public Long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(Long milliseconds) {
        this.milliseconds = milliseconds;
    }

}
