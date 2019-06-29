package com.example.NaucnaCentrala.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NaucnaCentrala.dto.ArticleDTO;
import com.example.NaucnaCentrala.model.Article;
import com.example.NaucnaCentrala.repository.ArticleRepository;


@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;
	
	public List<Article> getAll(){
		return articleRepository.findAll();
	}
	
	public Article getByTitle(String title){
		return articleRepository.getByTitle(title);
	}
	
	public void addArticle(Article u) {
		articleRepository.save(u);
	}
	
	public void updateArticle(Article u) {
		articleRepository.save(u);
	}
	
	public Article getOne(Long id) {
		return articleRepository.getOne(id);
	}
		
	public void deleteArticle(Long id) {
		articleRepository.deleteById(id);
	}
	
	public void deleteAllArticles() {
		articleRepository.deleteAll();
	}
	
	public Boolean existsArticle(long id) {
		return articleRepository.existsById(id);
	}
	
	public Article mapDTO(ArticleDTO articleDto){
			
		Article article = new Article();
		
		article.setId(articleDto.getId());
		return article;
	}
	
	public ArticleDTO mapToDTO(Article article){
		return new ArticleDTO(article);
	}
	
	public List<ArticleDTO> mapAllToDTO(){
		
		List<Article> articles = getAll();
		List<ArticleDTO> articleDTOs = new ArrayList<>();
		
		for(Article r : articles){
			articleDTOs.add(mapToDTO(r));
		}
		return articleDTOs;
	}
	
	public String getPdfText(String fileName) throws IOException {
		
		//Loading an existing document
	      File file = new File(fileName);
	      PDDocument document = PDDocument.load(file);
	      String text = "";
	      //Instantiate PDFTextStripper class
	      PDFTextStripper pdfStripper = new PDFTextStripper();

	      //Retrieving text from PDF document
	      text = pdfStripper.getText(document);

	      //Closing the document
	      document.close();
	      return text;
	}
}