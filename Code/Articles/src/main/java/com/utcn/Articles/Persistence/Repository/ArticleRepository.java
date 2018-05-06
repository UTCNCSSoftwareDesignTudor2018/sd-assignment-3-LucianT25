package com.utcn.Articles.Persistence.Repository;

import com.utcn.Articles.Persistence.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
    List<Article> findAll();
}