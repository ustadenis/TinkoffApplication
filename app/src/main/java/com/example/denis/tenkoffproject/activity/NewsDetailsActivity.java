package com.example.denis.tenkoffproject.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denis.tenkoffproject.R;
import com.example.denis.tenkoffproject.model.getnewsdetailsmodel.Payload;
import com.example.denis.tenkoffproject.presenter.NewsDetailsActivityPresenter;
import com.example.denis.tenkoffproject.presenter.NewsDetailsActivityPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewsDetailsActivity extends AppCompatActivity implements NewsDetailsView {

    private static final String TAG = NewsDetailsActivity.class.getSimpleName();

    private String mNewsID;

    private NewsDetailsActivityPresenter mPresenter;

    private Unbinder mUnbinder;

    @BindView(R.id.details_date) TextView mDateTV;
    @BindView(R.id.details_title) TextView mTitleTV;
    @BindView(R.id.details_message) TextView mMessageTV;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        mUnbinder = ButterKnife.bind(this);

        initActionBar();

        Intent intent = getIntent();
        if (intent.hasExtra(HomeActivity.NEWS_EXTRA)) {
            mNewsID = intent.getStringExtra(HomeActivity.NEWS_EXTRA);
        }

        mPresenter = new NewsDetailsActivityPresenterImpl(this);
        mPresenter.getNewsDetails(mNewsID);

        mProgress = new ProgressDialog(this);
        mProgress.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void onDataReady(Payload news) {
        updateUI(news);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        mProgress.hide();
    }

    private void updateUI(Payload news) {
        mDateTV.setText(news.getCreationDate());
        mTitleTV.setText(news.getTitle().getName());
        mMessageTV.setText(Html.fromHtml(news.getContent()));

        collapsingToolbarLayout.setTitle(news.getTitle().getName());

        mProgress.hide();
    }

    private void initActionBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
