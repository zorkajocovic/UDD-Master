package com.example.NaucnaCentrala.lucene.model;

import java.util.ArrayList;
import java.util.List;

public class ComplexQueryDto {

	private List<QueryDto> values = new ArrayList<>();;
	private List<String> operations = new ArrayList<>();;
	private boolean phrase;
	
	public List<QueryDto> getValues() {
		return values;
	}
	
	public void setValues(List<QueryDto> values) {
		this.values = values;
	}
	
	public List<String> getOperations() {
		return operations;
	}
	
	public void setOperations(List<String> operations) {
		this.operations = operations;
	}
	
	public boolean isPhrase() {
		return phrase;
	}
	
	public void setPhrase(boolean phrase) {
		this.phrase = phrase;
	}
	
}

