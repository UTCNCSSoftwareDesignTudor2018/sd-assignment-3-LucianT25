package com.utcn.Articles.Communication;

import com.google.gson.Gson;
import com.utcn.Articles.Persistence.Entity.Article;
import com.utcn.Articles.Persistence.Entity.Writer;
import com.utcn.Articles.Service.ArticleService;
import com.utcn.Articles.Service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ArticleController {

    private ArticleService articleService;
    private WriterService writerService;
    private WebSocketController webSocketController;

    @Autowired
    ArticleController(ArticleService articleService, WriterService writerService, WebSocketController webSocketController) {
        this.articleService = articleService;
        this.writerService = writerService;
        this.webSocketController = webSocketController;
    }

    @RequestMapping(value = "/{username}/get", method = RequestMethod.GET)
    public Article getArticleByName(@RequestParam String articleName) {
        return articleService.getArticle(articleName);
    }

    @RequestMapping(value = "/article/list", method = RequestMethod.GET)
    public List<Article> getArticles() {
        System.out.println("Retrieving articles... ");
        return articleService.getArticles();
    }

    @RequestMapping(value = "/{username}/delete", method = RequestMethod.POST)
    public void deleteArticle(@RequestBody String articleName) {
        articleService.removeArticle(articleName);
        try {
            webSocketController.updateArticles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{username}/post", method = RequestMethod.POST)
    public void postArticle(@RequestBody String articlejson, @PathVariable String username) {
        Gson gson = new Gson();
        Article article = gson.fromJson(articlejson, Article.class);
        System.out.println("Received insert request "+ article.getTitle() + " from ID: "+username);
        Writer writer = writerService.getWriterByUsername(username);
        article.setAuthor(writer);
        article.setId(Long.valueOf(0));
        articleService.addArticle(article);
        try {
            webSocketController.updateArticles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
