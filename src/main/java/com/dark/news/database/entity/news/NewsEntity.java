package com.dark.news.database.entity.news;

import com.dark.news.service.model.NewsModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "newsCatalog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE newsCatalog SET archived = true WHERE id=?")

public class NewsEntity {
    @Id
    @Column(name = "newsId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "dateTime")
    private LocalDateTime dateTime;
    @Column(name = "brief")
    private String brief;
    @Column(name = "content")
    private String content;
    @Column(name = "archived")
    private boolean archived;

    public NewsEntity(NewsModel newsModel) {
        this.id = newsModel.getId();
        this.title = newsModel.getTitle();
        this.brief = newsModel.getBrief();
        this.content = newsModel.getContent();
        this.dateTime = newsModel.getDateTime();
    }
}
