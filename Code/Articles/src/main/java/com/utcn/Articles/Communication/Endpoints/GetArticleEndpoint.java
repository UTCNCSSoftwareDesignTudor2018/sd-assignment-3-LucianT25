package com.utcn.Articles.Communication.Endpoints;

import com.utcn.Articles.Communication.Decoders.ArticleDecoder;
import com.utcn.Articles.Communication.Encoders.ArticleEncoder;
import com.utcn.Articles.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Controller
@ServerEndpoint(value = "/article/{name}", decoders = ArticleDecoder.class, encoders = ArticleEncoder.class)
public class GetArticleEndpoint {
    private ArticleService articleService;
    private Session session;

    @Autowired
    public GetArticleEndpoint(ArticleService articleService) {
        this.articleService = articleService;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnMessage
    public void onMessage(Session session, @PathParam("name") String articleName) throws IOException, EncodeException {
        session.getBasicRemote().sendObject(articleService.getArticle(articleName));
    }

    @OnClose
    public void onClose(Session session) throws IOException {

    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}
