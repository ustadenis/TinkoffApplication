package com.example.denis.tenkoffproject.presenter;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.denis.tenkoffproject.R;
import com.example.denis.tenkoffproject.activity.HomeView;
import com.example.denis.tenkoffproject.model.network.getnewsmodel.GetNewsModel;
import com.example.denis.tenkoffproject.model.network.getnewsmodel.NewsModel;
import com.example.denis.tenkoffproject.model.storage.AppDatabase;
import com.example.denis.tenkoffproject.model.storage.news.NewsConverter;
import com.example.denis.tenkoffproject.model.storage.news.NewsDao;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denis on 10.06.2017.
 */

public class HomeActivityPresenterImpl implements HomeActivityPresenter {

    private static final String HOST = "https://api.tinkoff.ru/";

    private final static String DATABASE_NAME = "news_db";

    private Context mContext;

    private HomeView mView;

    private Retrofit mRetrofit;

    private AppDatabase mDataBase;

    private CompositeDisposable mDisposables;

    public HomeActivityPresenterImpl(Context context, HomeView view) {
        mContext = context;
        mView = view;
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mDataBase = Room.databaseBuilder(mContext, AppDatabase.class, DATABASE_NAME).build();
        mDisposables = new CompositeDisposable();
    }

    @Override
    public void getNews() {
        Disposable disposable = mDataBase.newsModel()
                .getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsEntities -> {
                                if (newsEntities != null && newsEntities.size() > 0) {
                                    mView.onDataReady(NewsConverter.toNewsList(newsEntities));
                                } else {
                                    updateNews();
                                }
                            },
                            error -> mView.onError(error.toString())
                );

        mDisposables.add(disposable);
    }

    @Override
    public void updateNews() {
        GetNewsModel model = mRetrofit.create(GetNewsModel.class);

        Disposable disposable = model.getNews()
                .subscribeOn(Schedulers.io())
                .doOnNext(payloadModel -> updateNewsOnDatabase(payloadModel.getPayload()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        payloadModel -> {
                            if (!payloadModel.getResultCode().equals("OK")) {
                                mView.onError(mContext.getString(R.string.server_error));
                            }
                        },
                        error -> mView.onError(error.toString())
                );

        mDisposables.add(disposable);
    }

    @Override
    public void onDestroy() {
        mDisposables.dispose();
    }

    private void updateNewsOnDatabase(List<NewsModel> news) {
        NewsDao newsModel = mDataBase.newsModel();
        newsModel.deleteNews();
        newsModel.insertNews(NewsConverter.toEntitiesList(news));
    }
}
