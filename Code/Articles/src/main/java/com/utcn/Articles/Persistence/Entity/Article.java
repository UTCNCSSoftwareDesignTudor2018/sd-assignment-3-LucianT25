package com.utcn.Articles.Persistence.Entity;

import com.utcn.Articles.ObserverSupport.Reader;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "Article")
public class Article{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @OneToOne(fetch = FetchType.EAGER)
    private Writer author;

    private String abstr;
    private String body;

    public Article(Long id, String title, Writer author, String abstr, String body) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.body = body;
        this.abstr = abstr;
    }

    public Article() {

    }

    public String getAbstr() {
        return abstr;
    }

    public void setAbstr(String abstr) {
        this.abstr = abstr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Writer getAuthor() {
        return author;
    }

    public void setAuthor(Writer author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
