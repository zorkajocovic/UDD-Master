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

import com.example.NaucnaCentrala.dto.MagazineDTO;
import com.example.NaucnaCentrala.model.Magazine;
import com.example.NaucnaCentrala.services.MagazineService;

@RestController
@RequestMapping(value = "api/magazine")
public class MagazineController {

	@Autowired
	MagazineService magazineService;
	
	@GetMapping("/allMagazines")
	public ResponseEntity<List<MagazineDTO>> getAllMagazines(){
		
		List<Magazine> magazines = magazineService.getAll();
		
		List<MagazineDTO> magazinesDTO = new ArrayList<>();
		
		for(Magazine c : magazines){
			magazinesDTO.add(magazineService.mapToDTO(c));
		}
		
		return new ResponseEntity<>(magazinesDTO, HttpStatus.OK);
	}
		
	@RequestMapping(value = "/{magazineId}", method = RequestMethod.GET)
	public ResponseEntity<MagazineDTO> getMagazine(@PathVariable Long magazineId) {
		Magazine c = magazineService.getMagazine(magazineId);
		if (c == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new MagazineDTO(c), HttpStatus.OK);
	}

	@RequestMapping(value = "/addMagazine", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MagazineDTO> addMagazine(@RequestBody MagazineDTO magazineDto) {
		
		Magazine c = magazineService.mapDTO(magazineDto);

		magazineService.addMagazine(c);

		return new ResponseEntity<>(new MagazineDTO(c), HttpStatus.CREATED);
	}
	
}
