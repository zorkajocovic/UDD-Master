package com.example.NaucnaCentrala.lucene.model;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.example.NaucnaCentrala.dto.AuthorDTO;
import com.example.NaucnaCentrala.model.Author;

@Document(indexName = IndexUnit.INDEX_NAME, type = IndexUnit.TYPE_NAME, shards = 1, replicas = 0)
public class IndexUnit {

	public static final String INDEX_NAME = "magazine";
	public static final String TYPE_NAME = "article";
	
	@Id
	@Field(type = FieldType.Text, index = false, store = true)
	private String id;
	
	@Field(type = FieldType.Text, index = true, store = true)
	private String title;
	
	@Field(type = FieldType.Text, index = true, store = true)
	private String keywords;

	@Field(type = FieldType.Text, index = true, store = true)
	private String magazineName;

	@Field(type = FieldType.Nested, index = true, store = true)
	private Author author;
	
	@Field(type = FieldType.Text, index = true, store = true)
	private String pdfText;
	
	@Field(type = FieldType.Text, index = true, store = true)
	private String pdfLocation;

	@Field(type = FieldType.Text, index = true, store = true)
	private String scienceAreaName;
	
	@Field(type = FieldType.Boolean, index = true, store = true)
	private boolean openAccessMagazine;
	
	public boolean getOpenAccessMagazine() {
		return openAccessMagazine;
	}

	public void setOpenAccessMagazine(boolean openAccessMagazine) {
		this.openAccessMagazine = openAccessMagazine;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.title = id;
	}
	
	public String getPdfLocation() {
		return pdfLocation;
	}

	public void setPdfLocation(String pdfLocation) {
		this.pdfLocation = pdfLocation;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getMagazineName() {
		return magazineName;
	}

	public void setMagazineName(String magazineName) {
		this.magazineName = magazineName;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getPdfText() {
		return pdfText;
	}

	public void setPdfText(String pdfText) {
		this.pdfText = pdfText;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keyWords) {
		this.keywords = keyWords;
	}
	
	public String getScienceAreaName() {
		return scienceAreaName;
	}

	public void setScienceAreaName(String scienceAreaName) {
		this.scienceAreaName = scienceAreaName;
	}

	
}
