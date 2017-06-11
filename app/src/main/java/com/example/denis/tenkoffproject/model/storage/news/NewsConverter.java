package com.example.denis.tenkoffproject.model.storage.news;

import com.example.denis.tenkoffproject.model.network.getnewsmodel.NewsModel;
import com.example.denis.tenkoffproject.model.network.getnewsmodel.PublicationDateModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 10.06.2017.
 */

public class NewsConverter {

    public static NewsModel toNews(NewsEntity entity) {
        NewsModel news = null;

        if (entity != null) {
            news = new NewsModel();
            news.setId(String.valueOf(entity.getId()));
            news.setName(entity.getTitle());
            news.setText(entity.getMessage());
            PublicationDateModel publicationDate = new PublicationDateModel();
            publicationDate.setMilliseconds(entity.getDate());
            news.setPublicationDate(publicationDate);
        }

        return news;
    }

    public static List<NewsModel> toNewsList(List<NewsEntity> entitys) {
        List<NewsModel> news = new ArrayList<>();

        for (NewsEntity entity:
             entitys) {
            news.add(NewsConverter.toNews(entity));
        }

        return news;
    }

    public static NewsEntity toEntity(NewsModel news) {
        NewsEntity entity = null;

        if (news != null) {
            entity = new NewsEntity();
            entity.setId(Integer.parseInt(news.getId()));
            entity.setMessage(news.getText());
            entity.setTitle(news.getName());
            entity.setDate(news.getPublicationDateInMS());
        }

        return entity;
    }

    public static List<NewsEntity> toEntitiesList(List<NewsModel> news) {
        List<NewsEntity> entities = new ArrayList<>();

        for (NewsModel currentNews:
                news) {
            entities.add(NewsConverter.toEntity(currentNews));
        }

        return entities;
    }
}
