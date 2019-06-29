package com.example.NaucnaCentrala.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NaucnaCentrala.dto.AuthorDTO;
import com.example.NaucnaCentrala.model.Author;
import com.example.NaucnaCentrala.repository.AuthorRepository;


@Service
public class AuthorService {

	@Autowired
	AuthorRepository authorRepository;
	
	public List<Author> getAll(){
		return authorRepository.findAll();
	}
	
	public void addAuthor(Author s) {
		authorRepository.save(s);
		
	}
	public void updateAuthor(Author s) {
		authorRepository.save(s);
	}
	public Author getAuthor(long id) {
		return authorRepository.getOne(id);
	}
	
	public void deleteAuthor(Long id) {
		authorRepository.deleteById(id);
	}
	
	public void deleteAllAuthors() {
		authorRepository.deleteAll();
	}
	
	public Boolean existsAuthor(Long id) {
		return authorRepository.existsById(id);
	}
	
	public Author mapDTO(AuthorDTO authorDto){
		
		Author author = new Author();
		author.setId(authorDto.getId());
		author.setName(authorDto.getName());
		author.setSurname(authorDto.getSurname());
		author.setEmail(authorDto.getEmail());
		author.setPassword(authorDto.getPassword());
		author.setCity(authorDto.getCity());
		author.setCountry(authorDto.getCountry());
		return author;
	}
	
	public AuthorDTO mapToDTO(Author author){
		return new AuthorDTO(author);
	}
	
	public List<AuthorDTO> mapAllToDTO(){
		
		List<Author> authors = getAll();
		List<AuthorDTO> articleDTOs = new ArrayList<>();
		
		for(Author r : authors){
			articleDTOs.add(mapToDTO(r));
		}
		return articleDTOs;
	}
}
