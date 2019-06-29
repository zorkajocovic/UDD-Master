package com.example.NaucnaCentrala.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the article database table.
 * 
 */
@Entity
@NamedQuery(name="Article.findAll", query="SELECT a FROM Article a")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="abstract")
	private String abstract_;

	@Column(name="key_words")
	private String keyWords;

	@Column(name="magazine_name")
	private String magazineName;

	@Column(name="pdf_location")
	private String pdfLocation;

	@Column(name="pdf_text")
	private String pdfText;

	@Column(name="science_area_name")
	private String scienceAreaName;

	private String title;

	//bi-directional many-to-one association to Author
	@ManyToOne
	private Author author;

	//bi-directional many-to-one association to Magazine
	@ManyToOne
	private Magazine magazine;

	public Article() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPdfText() {
		return pdfText;
	}

	public void setPdfText(String pdfText) {
		this.pdfText = pdfText;
	}
	
	public String getAbstract_() {
		return abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getMagazineName() {
		return magazineName;
	}

	public void setMagazineName(String magazineName) {
		this.magazineName = magazineName;
	}

	public String getPdfLocation() {
		return pdfLocation;
	}

	public void setPdfLocation(String pdfLocation) {
		this.pdfLocation = pdfLocation;
	}

	public String getScienceAreaName() {
		return scienceAreaName;
	}

	public void setScienceAreaName(String scienceAreaName) {
		this.scienceAreaName = scienceAreaName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	
}