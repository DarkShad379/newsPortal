package com.dark.news.service;

import com.dark.news.service.model.NewsModel;

import java.util.List;

public interface NewsService {
    List<NewsModel> getAllActualNews();

    List<NewsModel> getAllArchivedNews();

    NewsModel getNews(Long id);

    void updateNews(NewsModel newsModel);

    void removeNews(NewsModel newsModel);

    void removeListOfNews(List<NewsModel> newsModelList);

}
