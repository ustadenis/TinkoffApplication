package com.example.denis.tenkoffproject.model.getnewsdetailsmodel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Denis on 10.06.2017.
 */

public interface GetDetailsModel {
    @GET("/v1/news_content")
    Call<GetDetailsResultModel> getNewsDetails(@Query("id") String id);
}
