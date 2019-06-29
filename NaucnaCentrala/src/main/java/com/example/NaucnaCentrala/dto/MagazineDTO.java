package com.example.NaucnaCentrala.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.NaucnaCentrala.model.Article;
import com.example.NaucnaCentrala.model.Magazine;


public class MagazineDTO {
	
	private Long id;

	private String issn;

	private boolean openaAccess;

	private String title;
	
	private String image;	
	
	private List<ArticleDTO> articles;
	
	public MagazineDTO() {
		
	}
	
	public MagazineDTO(Magazine magazine) {
		
		this.setId(magazine.getId());
		this.setIssn(magazine.getIssn());
		this.setOpenaccess(magazine.isOpenAccess());
		this.setTitle(magazine.getTitle());
		this.setImage(magazine.getImage());
		//this.editor = new EditorDTO(magazine.getEditor());
		
		/*this.articles = new ArrayList<ArticleDTO>();
		for(Article p : magazine.getArticles()){
			ArticleDTO articleDTO = new ArticleDTO(p);
			this.articles.add(articleDTO);
		}*/
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public boolean getOpenaccess() {
		return openaAccess;
	}

	public void setOpenaccess(boolean openaAccess) {
		this.openaAccess = openaAccess;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ArticleDTO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleDTO> articles) {
		this.articles = articles;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
