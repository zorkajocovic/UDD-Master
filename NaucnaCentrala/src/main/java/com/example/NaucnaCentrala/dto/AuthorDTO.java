package com.example.NaucnaCentrala.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.NaucnaCentrala.model.Article;
import com.example.NaucnaCentrala.model.Author;

public class AuthorDTO {
	
	private Long id;

	private String city;

	private String country;

	private String email;

	private String name;

	private String password;

	private String surname;

	private List<ArticleDTO> articles;
	
	public AuthorDTO() {
		
	}
	
	public AuthorDTO(Author author) {
		
		this.setId(author.getId());
		this.setCity(author.getCity());
		this.setCountry(author.getCountry());
		this.setName(author.getName());
		this.setSurname(author.getSurname());
		this.setEmail(author.getEmail());
		this.setPassword(author.getPassword());

		this.articles = new ArrayList<ArticleDTO>();
		for(Article p : author.getArticles()){
			ArticleDTO articleDTO = new ArticleDTO(p);
			this.articles.add(articleDTO);
		}
		
		/*this.coauthors = new ArrayList<CoauthorDTO>();
		for(Authorcoauthor p : author.getAuthorcoauthors()){
			CoauthorDTO coauthorDTO = new CoauthorDTO(p.getCoauthor());
			this.coauthors.add(coauthorDTO);
		}*/
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<ArticleDTO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleDTO> articles) {
		this.articles = articles;
	}

}
