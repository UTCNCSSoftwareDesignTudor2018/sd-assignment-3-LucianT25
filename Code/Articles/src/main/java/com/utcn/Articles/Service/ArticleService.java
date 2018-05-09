package com.utcn.Articles.Service;

import com.utcn.Articles.Persistence.Entity.Article;
import com.utcn.Articles.Persistence.Repository.ArticleRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    public ArticleService() {
    }

    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    public void removeArticle(String articleName) {
        articleRepository.delete(getArticle(articleName));
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article getArticle(String articleName) {
        return articleRepository.findArticleByTitle(articleName);
    }


}
