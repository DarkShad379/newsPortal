package com.dark.news.service.model;

import com.dark.news.database.entity.news.NewsEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NewsModel {
    private long id;
    private String title;
    private LocalDateTime dateTime;
    private String brief;
    private String content;

    public NewsModel(NewsEntity newsEntity) {
        this.id = newsEntity.getId();
        this.title = newsEntity.getTitle();
        this.brief = newsEntity.getBrief();
        this.content = newsEntity.getContent();
        this.dateTime = newsEntity.getDateTime();
    }
}
