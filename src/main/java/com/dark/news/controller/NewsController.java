package com.dark.news.controller;

import com.dark.news.service.NewsService;
import com.dark.news.service.model.NewsModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ModelAndView getAllActualNews(ModelMap model) {
        List<NewsModel> newsList = newsService.getAllActualNews();
        model.addAllAttributes(newsList);
        return new ModelAndView("/news", model);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("archived")
    public ModelAndView getAllArchivedNews(ModelMap model) {
        List<NewsModel> newsList = newsService.getAllArchivedNews();
        model.addAllAttributes(newsList);
        return new ModelAndView("/news", model);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("edit/{id}")
    public ModelAndView getNewsForEditById(@PathVariable int id, ModelMap model) {
        NewsModel selected = newsService.getNews(id);
        model.addAttribute(selected);
        return new ModelAndView("/news/edit", model);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("edit/{id}")
    public ModelAndView editNewsById(BindingResult bindingResult, @Valid @ModelAttribute("selectedNews") NewsModel selectedNews, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/news/edit/", model);
        } else {
            newsService.updateNews(selectedNews);
            return new ModelAndView("redirect:..", model);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("archive")
    public ModelAndView archiveNewsList(@ModelAttribute("selectedNewsList") List<Integer> selectedNewsIdList) {
        newsService.archiveListOfNews(selectedNewsIdList);
        return new ModelAndView("/news");
    }

    @GetMapping("/add")
    public ModelAndView getAddNewsPage(ModelMap model) {
        model.addAttribute("newNewsModel", new NewsModel());
        return new ModelAndView("/add", model);
    }

    @Validated
    @PostMapping("/add")
    public ModelAndView addNews(@Valid @ModelAttribute("newNewsModel") NewsModel newNewsModel, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            newsService.updateNews(newNewsModel);
            return new ModelAndView("redirect:");
        } else return new ModelAndView("news/add");
    }
}
