package com.utcn.Articles.ObserverSupport;

import java.util.Observable;
import java.util.Observer;

public class Reader implements Observer {

    private Long id;
    private Long articleId;

    @Override
    public void update(Observable o, Object arg) {

    }


}
