package com.dark.news.service;

import com.dark.news.service.model.NewsModel;

import java.util.List;

public interface NewsService {
    List<NewsModel> getAllActualNews();

    List<NewsModel> getAllArchivedNews();

    NewsModel getNews(Integer id);

    NewsModel updateNews(NewsModel newsModel);

    void archiveNews(NewsModel newsModel);

    void archiveListOfNews(List<Integer> newsIdModelList);

}
