package com.utcn.Articles.Communication.Endpoints;

import com.google.gson.Gson;
import com.utcn.Articles.Persistence.Entity.Article;
import com.utcn.Articles.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import javax.inject.Inject;
import java.util.List;

@Controller
public class ArticleController {

    @Inject
    ArticleService articleService;

    SimpMessagingTemplate template;

    @Autowired
    ArticleController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/get/list")
    public void updateArticles() throws Exception {
        List<Article> articles = articleService.getArticles();;
        Gson gson = new Gson();
        String json = gson.toJson(articles);
        this.template.convertAndSend("/list", json);
    }

}

