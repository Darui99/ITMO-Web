package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;

import java.util.List;

public interface ArticleRepository {
    Article find(long id);
    void save(Article event);
    List<Article> findAll();
    void setHidden(long id, boolean hidden);
}
