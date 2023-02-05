package com.dark.news.database.repository;

import com.dark.news.database.entity.news.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
}