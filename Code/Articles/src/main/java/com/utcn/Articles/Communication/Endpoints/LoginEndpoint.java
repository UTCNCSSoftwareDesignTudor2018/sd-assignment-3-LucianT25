package com.utcn.Articles.Communication.Endpoints;

import com.utcn.Articles.Communication.Credentials;
import com.utcn.Articles.Communication.Decoders.ArticleDecoder;
import com.utcn.Articles.Communication.Decoders.CredentialsDecoder;
import com.utcn.Articles.Communication.Encoders.ArticleEncoder;
import com.utcn.Articles.Persistence.Entity.Writer;
import com.utcn.Articles.Service.ArticleService;
import com.utcn.Articles.Service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Controller
@ServerEndpoint(value = "/login", decoders = CredentialsDecoder.class)
public class LoginEndpoint {
    private WriterService writerService;
    private Session session;

    @Autowired
    public LoginEndpoint(WriterService writerService) {
        this.writerService = writerService;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnMessage
    public void onMessage(Session session, Credentials credentials) throws IOException, EncodeException {
        Writer writer = writerService.getWriterByUsername(credentials.getUsername());
        if(writer.getPassword().equals(credentials.getPassword())) {
            session.getBasicRemote().sendObject(true);
        } else {
            session.getBasicRemote().sendObject(false);
        }

    }

    @OnClose
    public void onClose(Session session) throws IOException {

    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}
