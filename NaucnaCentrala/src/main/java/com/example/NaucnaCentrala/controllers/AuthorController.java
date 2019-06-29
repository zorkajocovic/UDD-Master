package com.example.NaucnaCentrala.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.NaucnaCentrala.dto.AuthorDTO;
import com.example.NaucnaCentrala.model.Author;
import com.example.NaucnaCentrala.services.AuthorService;

@RestController
@RequestMapping(value = "api/author")
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
	@GetMapping("/allAuthors")
	public ResponseEntity<List<AuthorDTO>> getAllAuthors(){
		
		List<Author> users = authorService.getAll();
		
		List<AuthorDTO> usersDTO = new ArrayList<>();
		
		for(Author c : users){
			usersDTO.add(authorService.mapToDTO(c));
		}
		
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Long id) {
		Author c = authorService.getAuthor(id);
		if (c == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new AuthorDTO(c), HttpStatus.OK);
	}

	@RequestMapping(value = "/addAuthor", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorDTO authorDto) {
		
		Author c = authorService.mapDTO(authorDto);

		authorService.addAuthor(c);

		return new ResponseEntity<>(new AuthorDTO(c), HttpStatus.CREATED);
	}
}
