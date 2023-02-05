package com.dark.news.service;

import com.dark.news.service.model.NewsModel;

import java.util.List;

public interface NewsService {
     List<NewsModel> getAllNews();

     NewsModel getNews(Long id);

     void updateNews(NewsModel newsModel);

     void removeNews(NewsModel newsModel);

     void removeListOfNews(List<NewsModel> newsModelList);

}
