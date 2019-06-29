package com.example.NaucnaCentrala.lucene.model;

public class ResultDto {
	
	private String nameMagazine;
	private String title;
	private String author;
	private String keywords;
	private String text;
	private String scientificField;
	private String location;
	private String highlight;
	
	public ResultDto() {
	}

	public ResultDto(String nameMagazine, String title, String author, String keywords, String text, String scientificField, String highlight) {
		this.nameMagazine = nameMagazine;
		this.title = title;
		this.author = author;
		this.keywords = keywords;
		this.text = text;
		this.scientificField = scientificField;
		this.location = location;
		this.highlight = highlight;
	}

	public String getNameMagazine() {
		return nameMagazine;
	}

	public void setNameMagazine(String nameMagazine) {
		this.nameMagazine = nameMagazine;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getScientificField() {
		return scientificField;
	}

	public void setScientificField(String scientificField) {
		this.scientificField = scientificField;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}
}
