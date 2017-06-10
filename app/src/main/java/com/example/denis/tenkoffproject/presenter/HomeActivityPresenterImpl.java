package com.example.denis.tenkoffproject.presenter;

import com.example.denis.tenkoffproject.activity.HomeView;
import com.example.denis.tenkoffproject.model.getnewsmodel.GetNewsModel;
import com.example.denis.tenkoffproject.model.getnewsmodel.NewsModel;
import com.example.denis.tenkoffproject.model.getnewsmodel.PayloadModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denis on 10.06.2017.
 */

public class HomeActivityPresenterImpl implements HomeActivityPresenter {

    private static final String HOST = "https://api.tinkoff.ru/";

    private HomeView mView;

    private Retrofit mRetrofit;

    public HomeActivityPresenterImpl(HomeView view) {
        mView = view;
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getNews() {
        GetNewsModel model = mRetrofit.create(GetNewsModel.class);

        model.getNews().enqueue(new Callback<PayloadModel>() {
            @Override
            public void onResponse(Call<PayloadModel> call, Response<PayloadModel> response) {
                if (response.isSuccessful()) {
                    PayloadModel payloadModel = response.body();
                    if (payloadModel.getResultCode().equals("OK")) {
                        List<NewsModel> news = payloadModel.getPayload();
                        if (mView != null) {
                            mView.onDataReady(news);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<PayloadModel> call, Throwable t) {
                if (mView != null) {
                    mView.onError(t.toString());
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
