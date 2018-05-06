package com.utcn.Articles.Communication.Encoders;

import com.google.gson.Gson;
import com.utcn.Articles.Persistence.Entity.Article;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ArticleEncoder implements Encoder.Text<Article>{

    @Override
    public String encode(Article article){
        Gson gson = new Gson();
        String json = gson.toJson(article);

        return json;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
