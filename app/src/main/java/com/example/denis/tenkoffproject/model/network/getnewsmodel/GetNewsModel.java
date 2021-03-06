package com.example.denis.tenkoffproject.model.network.getnewsmodel;

import io.reactivex.Observable;

import retrofit2.http.GET;

/**
 * Created by Denis on 09.06.2017.
 */

public interface GetNewsModel {
    @GET("/v1/news")
    Observable<PayloadModel> getNews();
}
