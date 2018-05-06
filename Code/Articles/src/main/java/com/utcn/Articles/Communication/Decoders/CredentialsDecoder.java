package com.utcn.Articles.Communication.Decoders;

import com.google.gson.Gson;
import com.utcn.Articles.Communication.Credentials;
import com.utcn.Articles.Persistence.Entity.Article;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class CredentialsDecoder implements Decoder.Text<Credentials>{

    @Override
    public Credentials decode(String s) throws DecodeException {
        Gson gson = new Gson();
        Credentials credentials = gson.fromJson(s, Credentials.class);
        return credentials;
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
