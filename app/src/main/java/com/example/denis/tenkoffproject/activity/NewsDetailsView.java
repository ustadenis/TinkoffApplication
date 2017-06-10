package com.example.denis.tenkoffproject.activity;

import com.example.denis.tenkoffproject.model.getnewsdetailsmodel.Payload;

/**
 * Created by Denis on 10.06.2017.
 */

public interface NewsDetailsView {
    void onDataReady(Payload news);
    void onError(String error);
}
