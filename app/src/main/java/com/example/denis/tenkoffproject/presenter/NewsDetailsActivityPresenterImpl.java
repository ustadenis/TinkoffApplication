package com.example.denis.tenkoffproject.presenter;

import com.example.denis.tenkoffproject.activity.HomeView;
import com.example.denis.tenkoffproject.activity.NewsDetailsView;
import com.example.denis.tenkoffproject.model.getnewsdetailsmodel.GetDetailsModel;
import com.example.denis.tenkoffproject.model.getnewsdetailsmodel.GetDetailsResultModel;
import com.example.denis.tenkoffproject.model.getnewsdetailsmodel.Payload;
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

public class NewsDetailsActivityPresenterImpl implements NewsDetailsActivityPresenter {

    private static final String HOST = "https://api.tinkoff.ru/";

    private NewsDetailsView mView;

    private Retrofit mRetrofit;

    public NewsDetailsActivityPresenterImpl(NewsDetailsView view) {
        mView = view;
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getNewsDetails(String id) {
        GetDetailsModel model = mRetrofit.create(GetDetailsModel.class);

        model.getNewsDetails(id).enqueue(new Callback<GetDetailsResultModel>() {
            @Override
            public void onResponse(Call<GetDetailsResultModel> call, Response<GetDetailsResultModel> response) {
                if (response.isSuccessful()) {
                    GetDetailsResultModel payloadModel = response.body();
                    if (payloadModel.getResultCode().equals("OK")) {
                        Payload news = payloadModel.getPayload();
                        if (mView != null) {
                            mView.onDataReady(news);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetDetailsResultModel> call, Throwable t) {
                if (mView != null) {
                    mView.onError(t.toString());
                }
            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
