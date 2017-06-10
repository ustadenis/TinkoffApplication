package com.example.denis.tenkoffproject.model.getnewsmodel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Denis on 09.06.2017.
 */

public interface GetNewsModel {
    @GET("/v1/news")
    Call<PayloadModel> getNews();
}
