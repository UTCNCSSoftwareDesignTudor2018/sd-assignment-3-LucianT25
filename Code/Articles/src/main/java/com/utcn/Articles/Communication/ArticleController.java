package com.utcn.Articles.Communication;

import com.google.gson.Gson;
import com.utcn.Articles.Persistence.Entity.Article;
import com.utcn.Articles.Persistence.Entity.Writer;
import com.utcn.Articles.Service.ArticleService;
import com.utcn.Articles.Service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ArticleController {

    private ArticleService articleService;
    private WriterService writerService;

    @Autowired
    ArticleController(ArticleService articleService, WriterService writerService) {
        this.articleService = articleService;
        this.writerService = writerService;
    }

    @RequestMapping(value = "/article/get", method = RequestMethod.GET)
    public Article getArticleByName(String articleName) {
        return articleService.getArticle(articleName);
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
    }


}
