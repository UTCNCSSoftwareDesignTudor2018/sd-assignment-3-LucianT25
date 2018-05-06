package com.utcn.Articles.Communication.Endpoints;

import com.utcn.Articles.Communication.Decoders.ArticleDecoder;
import com.utcn.Articles.Communication.Encoders.ArticleEncoder;
import com.utcn.Articles.Persistence.Entity.Article;
import com.utcn.Articles.Persistence.Entity.Writer;
import com.utcn.Articles.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/article/create/{username}", encoders = ArticleEncoder.class, decoders = ArticleDecoder.class)
public class CreateArticleEndpoint {
    private ArticleService articleService;
    private Session session;
    private Writer writer;

    @Autowired
    public CreateArticleEndpoint(ArticleService articleService) {
        this.articleService = articleService;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnMessage
    public void onMessage(Session session, Article article) throws IOException, EncodeException {
        articleService.addArticle(article);
    }

    @OnClose
    public void onClose(Session session) throws IOException {

    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}
