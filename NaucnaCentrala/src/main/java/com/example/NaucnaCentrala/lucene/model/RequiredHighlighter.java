package com.example.NaucnaCentrala.lucene.model;

public class RequiredHighlighter {

	private String fieldName;
	private String value;
	
	public RequiredHighlighter() {
	}

	public RequiredHighlighter(String fieldName, String value) {
		this.fieldName = fieldName;
		this.value = value;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getValue() {
		return value;
	}
}
