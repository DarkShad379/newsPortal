package com.dark.news.service.impl;

import com.dark.news.database.entity.news.NewsEntity;
import com.dark.news.database.repository.NewsRepository;
import com.dark.news.exceptions.NoSuchEntryInDatabaseException;
import com.dark.news.service.model.NewsModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NewsServiceImplTest {
    @Mock
    private NewsRepository newsRepository;
    @InjectMocks
    private NewsServiceImpl newsService;

    @Test
    void should_return_archived_news() {
        List<NewsEntity> mockEntityList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NewsEntity mockEntity = new NewsEntity();
            mockEntity.setId(i);
            mockEntity.setArchived(true);
            mockEntityList.add(mockEntity);
        }
        when(newsRepository.findAllArchivedNews()).thenReturn(mockEntityList);
        List<NewsModel> actualNews = newsService.getAllArchivedNews();
        Optional<NewsModel> archivedNews = actualNews.stream().filter(newsEntity -> !newsEntity.isArchived()).findAny();
        assertThat("Found non-archived news from actual request request", archivedNews.isEmpty());
    }

    @Test
    void should_return_actual_news() {
        List<NewsEntity> mockEntityList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NewsEntity mockEntity = new NewsEntity();
            mockEntity.setId(i);
            mockEntityList.add(mockEntity);
        }
        when(newsRepository.findAllActualNews()).thenReturn(mockEntityList);
        List<NewsModel> actualNews = newsService.getAllActualNews();
        Optional<NewsModel> archivedNews = actualNews.stream().filter(NewsModel::isArchived).findAny();
        assertThat("Found archived news from non archived request", archivedNews.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 5, 10, Integer.MAX_VALUE})
    void should_find_one_news_by_id(int newsId) {
        NewsEntity mockEntity = new NewsEntity();
        mockEntity.setId(newsId);
        mockEntity.setTitle("Тестовая новость");
        when(newsRepository.findById(newsId)).thenReturn(Optional.of(mockEntity));
        NewsModel foundEntity = newsService.getNews(newsId);
        assertThat("ID not identical", Objects.equals(foundEntity.getId(), mockEntity.getId()));
    }

    @Test
    void should_not_find_news_that_doesnt_exists() {
        when(newsRepository.findById(anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchEntryInDatabaseException.class, () -> {
            newsService.getNews(1);
        });
    }

    @Test
    void should_save_and_return_one_news() {
        NewsModel mockModel = new NewsModel();
        mockModel.setId(1);
        mockModel.setTitle("Тестовый тайтл");
        mockModel.setContent("Очень важный контент");
        mockModel.setDateTime(LocalDateTime.now());
        mockModel.setBrief("короткое описание");
        when(newsRepository.save(any(NewsEntity.class))).then(returnsFirstArg());
        NewsModel savedModel = newsService.updateNews(mockModel);
        assertThat("Models are not equals", mockModel.equals(savedModel));

    }

    @Test
    void should_archive_list_of_news() {
        ArrayList<Integer> mockIdList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mockIdList.add(i);
        }
        newsService.archiveListOfNews(mockIdList);
        for (Integer newsId : mockIdList
        ) {
            Mockito.verify(newsRepository).deleteById(newsId);
        }
    }

}