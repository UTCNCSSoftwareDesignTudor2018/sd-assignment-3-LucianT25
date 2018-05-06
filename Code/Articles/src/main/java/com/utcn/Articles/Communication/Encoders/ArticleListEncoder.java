package com.utcn.Articles.Communication.Encoders;

import com.google.gson.Gson;
import com.utcn.Articles.Persistence.Entity.Article;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.List;

public class ArticleListEncoder implements Encoder.Text<List<Article>> {
    @Override
    public String encode(List<Article> articles){
        Gson gson = new Gson();
        String json = gson.toJson(articles);

        return json;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
