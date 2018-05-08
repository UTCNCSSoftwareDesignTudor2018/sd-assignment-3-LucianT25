package com.utcn.Articles.Service;

import com.utcn.Articles.ObserverSupport.ArticleManager;
import com.utcn.Articles.ObserverSupport.Reader;
import com.utcn.Articles.Persistence.Entity.Article;
import com.utcn.Articles.Persistence.Repository.ArticleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    private ArticleManager articleManager;

    public ArticleService() {
        this.articleManager = ArticleManager.getInstance();
        //articleManager.setArticles(getArticles());
        //System.out.println("Did it! "+articleManager.getArticles().toArray());
    }

    public void addArticle(Article article) {
        articleRepository.save(article);
      //  articleManager.addArticle(article);
    }

    public void removeArticle(Article article) {
        articleRepository.delete(article);
       // articleManager.removeArticle(article);
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article getArticle(String articleName) {
        return articleRepository.findArticleByTitle(articleName);
    }


}
