package com.dark.news.database.repository;

import com.dark.news.database.entity.news.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    @Query("select n from News n where n.archived = false order by n.name")
    List<NewsEntity> findAllActualNews();

    @Query("select n from News n where n.archived = true order by n.name")
    List<NewsEntity> findAllArchivedNews();
}