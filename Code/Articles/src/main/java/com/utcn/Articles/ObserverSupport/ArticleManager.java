package com.utcn.Articles.ObserverSupport;

import com.utcn.Articles.Persistence.Entity.Article;
import com.utcn.Articles.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ArticleManager extends Observable {

    public static ArticleManager instance;
    private List<Article> articles;

    protected ArticleManager() {

    }

    public static ArticleManager getInstance() {
        if(instance == null) {
            instance = new ArticleManager();
        }
        return instance;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return this.articles;
    }

    public void addArticle(Article article) {
        articles.add(article);
        notifyObservers();
    }

    public void removeArticle(Article article) {
        articles.remove(article);
        notifyObservers();
    }

}
