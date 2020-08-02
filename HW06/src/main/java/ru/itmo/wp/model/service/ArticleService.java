package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateArticle(String title, String text) throws ValidationException {
        if (title == null || title.isEmpty()) {
            throw new ValidationException("Empty title");
        }
        if (text == null || text.isEmpty()) {
            throw new ValidationException("Empty text");
        }
    }

    public void addArticle(Long userId, String title, String text) {
        Article article = new Article();
        article.setUserId(userId);
        article.setTitle(title);
        article.setText(text);
        article.setHidden(false);
        articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article find(long id) {
        return articleRepository.find(id);
    }

    public void setHidden(long id, boolean hidden) {
        articleRepository.setHidden(id, hidden);
    }
}
