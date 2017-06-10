package com.example.denis.tenkoffproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.denis.tenkoffproject.model.getnewsmodel.NewsModel;

import java.util.List;

/**
 * Created by Denis on 09.06.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<NewsModel> mNews;
    private Context mContext;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(NewsModel news);
    }

    public RecyclerViewAdapter(Context context, List<NewsModel> news) {
        mContext = context;
        mNews = news;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NewsModel news = mNews.get(position);
        holder.mItemTitle.setText(news.getName());
        holder.mItemMessage.setText(Html.fromHtml(news.getText()));
        holder.mItemDate.setText(news.getPublicationDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(news);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public void setNews(List<NewsModel> news) {
        mNews = news;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mItemTitle;
        private TextView mItemMessage;
        private TextView mItemDate;

        public ViewHolder(View itemView) {
            super(itemView);

            mItemMessage = (TextView) itemView.findViewById(R.id.item_message);
            mItemTitle = (TextView) itemView.findViewById(R.id.item_title);
            mItemDate = (TextView) itemView.findViewById(R.id.item_date);
        }
    }
}
