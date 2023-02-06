package com.dark.news.service.model;

import com.dark.news.database.entity.news.NewsEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NewsModel {
    @NotNull
    private long id;
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    private LocalDateTime dateTime;
    @NotNull
    @NotEmpty
    private String brief;
    @NotNull
    @NotEmpty
    private String content;

    public NewsModel(NewsEntity newsEntity) {
        this.id = newsEntity.getId();
        this.title = newsEntity.getTitle();
        this.brief = newsEntity.getBrief();
        this.content = newsEntity.getContent();
        this.dateTime = newsEntity.getDateTime();
    }
}
