package com.utcn.Articles.Communication.Endpoints;

import com.utcn.Articles.Communication.Decoders.ArticleListDecoder;
import com.utcn.Articles.Communication.Encoders.ArticleEncoder;
import com.utcn.Articles.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
@ServerEndpoint(value = "/article/all", decoders = ArticleListDecoder.class, encoders = ArticleEncoder.class)
public class ArticlesListEndpoint {

    private ArticleService articleService;

    private Session session;
    private static Set<ArticlesListEndpoint> articlesListEndpoints = new CopyOnWriteArraySet<>();
    private static Map<Session, ArticlesListEndpoint> readers = new HashMap<>();

    @Autowired
    public ArticlesListEndpoint(ArticleService articleService) {
        this.articleService = articleService;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        articlesListEndpoints.add(this);
        readers.put(session, this);
    }

    @OnMessage
    public void onMessage(Session session, String articleName) throws IOException, EncodeException {
        readers.get(session).session.getBasicRemote().sendObject(articleService.getArticles());
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        readers.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    public static void updateArticleList() throws IOException, EncodeException {
            articlesListEndpoints.forEach(endpoint -> {
                synchronized (endpoint) {
                    try {
                        endpoint.session.getBasicRemote().
                                sendObject("updateList");
                    } catch (IOException | EncodeException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
}
