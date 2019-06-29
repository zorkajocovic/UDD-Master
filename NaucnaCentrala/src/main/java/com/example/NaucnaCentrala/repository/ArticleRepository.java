package com.example.NaucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NaucnaCentrala.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	Article getByTitle(String title);
}
