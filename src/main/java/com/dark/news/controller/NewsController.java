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
    public ModelAndView getAllNews(ModelMap model) {
        List<NewsModel> newsList = newsService.getAllNews();
        model.addAllAttributes(newsList);
        return new ModelAndView("/news", model);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("edit/{id}")
    public ModelAndView getNewsForEditById(@PathVariable long id, ModelMap model) {
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
    @PostMapping("remove")
    public ModelAndView removeNewsList(@ModelAttribute("selectedNewsList") List<NewsModel> selectedNewsList) {
        newsService.removeListOfNews(selectedNewsList);
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
