package com.dark.news.service.impl;

import com.dark.news.database.entity.news.NewsEntity;
import com.dark.news.database.repository.NewsRepository;
import com.dark.news.service.model.NewsModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NewsServiceImplTest {
    @Mock
    private NewsRepository newsRepository;
    @InjectMocks
    private NewsServiceImpl newsService;

    @Test
    void getAllArchivedNews() {
        //TODO
    }

    @Test
    void getAllActualNews() {
        //TODO
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 5, 10, Integer.MAX_VALUE})
    void getNews(int newsId) {
        NewsEntity mockEntity = new NewsEntity();
        mockEntity.setId(newsId);
        mockEntity.setTitle("Тестовая новость");
        when(newsRepository.findById(newsId)).thenReturn(Optional.of(mockEntity));
        NewsModel findedEntity = newsService.getNews(newsId);
        assertThat("ID identical", Objects.equals(findedEntity.getId(), mockEntity.getId()));
    }

    @Test
    void updateNews() {
        //TODO
    }

    @Test
    void removeNews() {
        //TODO
    }

    @Test
    void removeListOfNews() {
        //TODO
    }
}