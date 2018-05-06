package com.utcn.Articles.Communication.Decoders;

import com.google.gson.Gson;
import com.utcn.Articles.Persistence.Entity.Article;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class ArticleDecoder implements Decoder.Text<Article>{

    @Override
    public Article decode(String s) throws DecodeException {
        Gson gson = new Gson();
        Article article = gson.fromJson(s, Article.class);
        return article;
    }

    @Override
    public boolean willDecode(String s) {
        return (s!=null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
