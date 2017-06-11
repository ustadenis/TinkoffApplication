package com.example.denis.tenkoffproject.model.network.getnewsdetailsmodel;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Denis on 10.06.2017.
 */

public interface GetDetailsModel {
    @GET("/v1/news_content")
    Observable<GetDetailsResultModel> getNewsDetails(@Query("id") String id);
}
