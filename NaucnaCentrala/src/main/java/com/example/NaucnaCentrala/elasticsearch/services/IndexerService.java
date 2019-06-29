package com.example.NaucnaCentrala.elasticsearch.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NaucnaCentrala.elasticsarch.repository.BookRepository;
import com.example.NaucnaCentrala.elasticsearch.filters.CyrilicLatinConverter;
import com.example.NaucnaCentrala.lucene.model.IndexUnit;

@Service
public class IndexerService {

	@Autowired
	private BookRepository repository;
	
	public IndexerService() {
	
	}
	
	public boolean update(IndexUnit unit){
		unit = repository.save(unit);
		if(unit!=null)
			return true;
		else
			return false;
	}
	
	public boolean add(IndexUnit unit){
		unit = repository.index(unit);
		if(unit!=null)
			return true;
		else
			return false;
	}
	
   public List<IndexUnit> findAll(){
        List<IndexUnit> target = new ArrayList<>();
        this.repository.findAll().forEach(target::add);
        return  target;
    }
   
	public static String changeCharacter(String text) {
		
		String text1 = text.toLowerCase();
		String text2 = CyrilicLatinConverter.cir2lat(text1);

		String text3 = text2.replaceAll("đ", "dj");
		String text4 = text3.replaceAll("č", "c");
		String text5 = text4.replaceAll("ć", "c");
		String text6 = text5.replaceAll("dž", "dz");
		String text7 = text6.replaceAll("š", "s");
		String text8 = text7.replaceAll("ž", "z");

		String text9 = text8.replaceAll("ђ", "dj");
		String text10 = text9.replaceAll("љ", "lj");
		String text11 = text10.replaceAll("њ", "nj");
		String text12 = text11.replaceAll("dj", "d");

		return text12;
	}

}