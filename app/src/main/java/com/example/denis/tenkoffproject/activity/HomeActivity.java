package com.example.denis.tenkoffproject.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.denis.tenkoffproject.model.network.getnewsmodel.NewsModel;
import com.example.denis.tenkoffproject.R;
import com.example.denis.tenkoffproject.RecyclerViewAdapter;
import com.example.denis.tenkoffproject.presenter.HomeActivityPresenter;
import com.example.denis.tenkoffproject.presenter.HomeActivityPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeActivity extends AppCompatActivity implements HomeView, SwipeRefreshLayout.OnRefreshListener, RecyclerViewAdapter.OnItemClickListener {

    public static final String NEWS_EXTRA = "news_id_extra";

    private Unbinder mUnbinder;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout) SwipeRefreshLayout mRefreshLayout;

    private RecyclerViewAdapter mRecyclerViewAdapter;

    private HomeActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mUnbinder = ButterKnife.bind(this);
        mPresenter = new HomeActivityPresenterImpl(this, this);

        mRecyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>());
        mRecyclerViewAdapter.setOnClickListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRefreshLayout.setOnRefreshListener(this);

        mPresenter.getNews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.tinkoff_site:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.tinkoff_site)));
                startActivity(browserIntent);
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mPresenter.onDestroy();
    }

    @Override
    public void onDataReady(List<NewsModel> news) {
        mRecyclerViewAdapter.setNews(news);
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.updateNews();
    }

    @Override
    public void onItemClick(NewsModel news) {
        Intent intent = new Intent(this, NewsDetailsActivity.class);
        intent.putExtra(NEWS_EXTRA, news.getId());
        startActivity(intent);
    }
}
