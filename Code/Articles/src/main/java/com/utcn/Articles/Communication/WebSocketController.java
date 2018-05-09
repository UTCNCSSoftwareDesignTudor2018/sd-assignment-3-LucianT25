package com.utcn.Articles.Communication;

import com.google.gson.Gson;
import com.utcn.Articles.Persistence.Entity.Article;
import com.utcn.Articles.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

import javax.inject.Inject;
import java.util.List;

@Controller
public class WebSocketController {

    @Inject
    ArticleService articleService;

    private SimpMessagingTemplate brokerMessagingTemplate;

    @Autowired
    WebSocketController(SimpMessagingTemplate brokerMessagingTemplate) {
        this.brokerMessagingTemplate = brokerMessagingTemplate;
    }

    @MessageMapping("/get/list")
    @SendTo("/list")
    public String getArticles(){
        List<Article> articles = articleService.getArticles();;
        Gson gson = new Gson();
        String json = gson.toJson(articles);
        return json;
    }

    public void updateArticles() {
        List<Article> articles = articleService.getArticles();;
        Gson gson = new Gson();
        String json = gson.toJson(articles);
        this.brokerMessagingTemplate.convertAndSend("/list", json);
    }

}

