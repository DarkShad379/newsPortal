package com.dark.news.service.impl;

import com.dark.news.database.entity.news.NewsEntity;
import com.dark.news.database.repository.NewsRepository;
import com.dark.news.exceptions.NoSuchEntryInDatabaseException;
import com.dark.news.service.NewsService;
import com.dark.news.service.model.NewsModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<NewsModel> getAllArchivedNews() {
        List<NewsEntity> entityNewsList = newsRepository.findAllArchivedNews();
        return convertNewsEntityListToModelList(entityNewsList);
    }

    @Override
    public List<NewsModel> getAllActualNews() {
        List<NewsEntity> entityNewsList = newsRepository.findAllActualNews();
        return convertNewsEntityListToModelList(entityNewsList);
    }

    private static ArrayList<NewsModel> convertNewsEntityListToModelList(List<NewsEntity> entityNewsList) {
        ArrayList<NewsModel> modelNewsList = new ArrayList<>();
        entityNewsList.forEach(newsEntity -> modelNewsList.add(new NewsModel(newsEntity)));
        return modelNewsList;
    }

    @Override
    public NewsModel getNews(Integer id) {
        Optional<NewsEntity> newsEntity = newsRepository.findById(id);
        if (newsEntity.isEmpty()) {
            throw new NoSuchEntryInDatabaseException("Cannot find news with id " + id);
        }
        return new NewsModel(newsEntity.get());
    }

    @Override
    public NewsModel updateNews(NewsModel newsModel) {
        NewsEntity newsEntity = new NewsEntity(newsModel);
        NewsEntity savedEntity = newsRepository.save(newsEntity);
        return new NewsModel(savedEntity);
    }

    @Override
    public void archiveNews(NewsModel newsModel) {
        NewsEntity newsEntity = new NewsEntity(newsModel);
        newsRepository.delete(newsEntity);
    }

    @Override
    public void archiveListOfNews(List<NewsModel> newsModelList) {
        newsModelList.forEach(newsModel -> newsRepository.delete(new NewsEntity(newsModel)));
    }
}
