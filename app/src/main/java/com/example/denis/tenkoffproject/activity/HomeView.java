package com.example.denis.tenkoffproject.activity;

import com.example.denis.tenkoffproject.model.network.getnewsmodel.NewsModel;

import java.util.List;

/**
 * Created by Denis on 10.06.2017.
 */

public interface HomeView {
    void onDataReady(List<NewsModel> news);
    void onError(String error);
}
