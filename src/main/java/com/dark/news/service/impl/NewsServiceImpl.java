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
    public List<NewsModel> getAllNews() {
        List<NewsEntity> entityNewsList = newsRepository.findAll();
        ArrayList<NewsModel> modelNewsList = new ArrayList<>();
        entityNewsList.forEach(newsEntity -> modelNewsList.add(new NewsModel(newsEntity)));
        return modelNewsList;
    }

    @Override
    public NewsModel getNews(Long id) {
        Optional<NewsEntity> newsEntity = newsRepository.findById(id);
        if (newsEntity.isEmpty()) {
            throw new NoSuchEntryInDatabaseException("Cannot find news with id " + id);
        }
        return new NewsModel(newsEntity.get());
    }

    @Override
    public void updateNews(NewsModel newsModel) {
        NewsEntity newsEntity = new NewsEntity(newsModel);
        newsRepository.save(newsEntity);
    }

    @Override
    public void removeNews(NewsModel newsModel) {
        NewsEntity newsEntity = new NewsEntity(newsModel);
        newsRepository.delete(newsEntity);
    }

    @Override
    public void removeListOfNews(List<NewsModel> newsModelList) {
        newsModelList.forEach(newsModel -> newsRepository.delete(new NewsEntity(newsModel)));
    }
}
