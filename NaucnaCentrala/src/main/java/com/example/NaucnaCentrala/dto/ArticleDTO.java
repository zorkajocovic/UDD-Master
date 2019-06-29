package com.example.NaucnaCentrala.dto;

import com.example.NaucnaCentrala.model.Article;

public class ArticleDTO {

	private Long id;

	private String _abstract;

	private String pdfLocation;

	private String pdfText;

	private String title;
	
	private String keyWords;

	private AuthorDTO author;

	private String magazineName;

	//private List<ReviewDTO> reviews;

	public ArticleDTO() {
		
	}
	
	public ArticleDTO(Article article) {

		this.setId(article.getId());
		this.setPdfLocation(article.getPdfLocation());
		this.setPdfText(article.getPdfText());
		this.set_abstract(article.getAbstract_());
		this.setTitle(article.getTitle());
		this.setKeyWords(article.getKeyWords());
		this.author = new AuthorDTO(article.getAuthor());
		this.setMagazineName(article.getMagazineName());
		
		/*this.reviews= new ArrayList<ReviewDTO>();
		for(Review p : article.getReviews()){
			ReviewDTO reviewDTO = new ReviewDTO(p);
			this.reviews.add(reviewDTO);
		}*/
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String get_abstract() {
		return _abstract;
	}

	public void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}

	public String getPdfLocation() {
		return pdfLocation;
	}

	public void setPdfLocation(String pdfLocation) {
		this.pdfLocation = pdfLocation;
	}

	public String getPdfText() {
		return pdfText;
	}

	public void setPdfText(String pdfText) {
		this.pdfText = pdfText;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	/*public List<ReviewDTO> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewDTO> reviews) {
		this.reviews = reviews;
	}*/

	public String getMagazineName() {
		return magazineName;
	}

	public void setMagazineName(String magazineName) {
		this.magazineName = magazineName;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
}
