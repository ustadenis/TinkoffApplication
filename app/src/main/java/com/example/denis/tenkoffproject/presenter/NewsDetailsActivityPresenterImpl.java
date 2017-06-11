package com.example.denis.tenkoffproject.presenter;

import com.example.denis.tenkoffproject.activity.NewsDetailsView;
import com.example.denis.tenkoffproject.model.network.getnewsdetailsmodel.GetDetailsModel;
import com.example.denis.tenkoffproject.model.network.getnewsdetailsmodel.GetDetailsResultModel;
import com.example.denis.tenkoffproject.model.network.getnewsdetailsmodel.Payload;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denis on 10.06.2017.
 */

public class NewsDetailsActivityPresenterImpl implements NewsDetailsActivityPresenter {

    private static final String HOST = "https://api.tinkoff.ru/";

    private NewsDetailsView mView;

    private Retrofit mRetrofit;

    private CompositeDisposable mDisposables;

    public NewsDetailsActivityPresenterImpl(NewsDetailsView view) {
        mView = view;
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mDisposables = new CompositeDisposable();
    }

    @Override
    public void getNewsDetails(String id) {
        GetDetailsModel model = mRetrofit.create(GetDetailsModel.class);

        Disposable disposable = model.getNewsDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getDetailsResultModel -> {
                            if (getDetailsResultModel.getResultCode().equals("OK")) {
                                Payload news = getDetailsResultModel.getPayload();
                                mView.onDataReady(news);
                            }
                        },
                        error -> mView.onError(error.toString()));

        mDisposables.add(disposable);
    }

    @Override
    public void onDestroy() {
        mDisposables.dispose();
    }
}
