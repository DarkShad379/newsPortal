package com.dark.news.database.repository;

import com.dark.news.database.entity.news.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsEntity, Integer> {
    @Query("select n from NewsEntity n where n.archived = false order by n.title")
    List<NewsEntity> findAllActualNews();

    @Query("select n from NewsEntity n where n.archived = true order by n.title")
    List<NewsEntity> findAllArchivedNews();
}