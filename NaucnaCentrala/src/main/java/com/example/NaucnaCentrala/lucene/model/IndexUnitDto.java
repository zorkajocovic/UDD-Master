package com.example.NaucnaCentrala.lucene.model;

public class IndexUnitDto {
	
	private String id;
    private String title;
    private String text;
    private String openAccessMagazine;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getOpenAccess() {
		return openAccessMagazine;
	}
	public void setOpenAccess(String openAccess) {
		this.openAccessMagazine = openAccess;
	}
    
    
}
