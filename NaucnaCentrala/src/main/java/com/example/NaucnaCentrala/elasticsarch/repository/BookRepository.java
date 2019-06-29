package com.example.NaucnaCentrala.elasticsarch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.NaucnaCentrala.lucene.model.IndexUnit;

@Repository
public interface BookRepository extends ElasticsearchRepository<IndexUnit, String> {
	
	//List<IndexUnit> findByMagazineName(String magazineName);
	//List<IndexUnit> findByTitle(String title);
	/*List<IndexUnit> findByAuthor(String autor);
	List<IndexUnit> findByKeywords(String keywords);
	List<IndexUnit> findByPdfText(String pdfText);
	List<IndexUnit> findByScienceAreaName(String scienceAreaName);*/
}