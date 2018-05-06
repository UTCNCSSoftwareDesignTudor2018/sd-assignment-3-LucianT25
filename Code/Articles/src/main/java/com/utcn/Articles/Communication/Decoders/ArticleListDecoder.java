package com.utcn.Articles.Communication.Decoders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.utcn.Articles.Persistence.Entity.Article;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.lang.reflect.Type;
import java.util.List;

public class ArticleListDecoder implements Decoder.Text<List<Article>>{
    @Override
    public List<Article> decode(String s) throws DecodeException {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Article>>(){}.getType();
        List<Article> articles = gson.fromJson(s, type);
        return articles;
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
