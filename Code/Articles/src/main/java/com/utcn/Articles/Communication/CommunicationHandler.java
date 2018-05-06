package com.utcn.Articles.Communication;

import com.utcn.Articles.Service.ArticleService;
import com.utcn.Articles.Persistence.Entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;

@Controller
public class CommunicationHandler extends TextWebSocketHandler{

    private ArticleService articleService;

    @Autowired
    public CommunicationHandler(ArticleService articleService) {
        this.articleService = articleService;
    }

    @MessageMapping("/articles/all")
    @SendTo("/topics/articlesList")
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    @MessageMapping("articles/add")
    public void addArticle(Article article) {
        articleService.addArticle(article);
    }


}
