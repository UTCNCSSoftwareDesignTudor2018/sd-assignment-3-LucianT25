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
    private Writer author;
    private String abstr;
    private String body;

    private ArrayList<Reader> readers;

    public Article(Long id, String title, Writer author, String abstr, String body) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.body = body;
        this.readers = new ArrayList<>();
        this.abstr = abstr;
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

    public void addReader(Reader reader) {
        this.readers.add(reader);
    }

    public void removeReader(Reader reader) {
        this.readers.remove(reader);
    }
}
