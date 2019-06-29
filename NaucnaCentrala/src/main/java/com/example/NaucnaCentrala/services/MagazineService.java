package com.example.NaucnaCentrala.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.NaucnaCentrala.dto.MagazineDTO;
import com.example.NaucnaCentrala.model.Magazine;
import com.example.NaucnaCentrala.repository.MagazineRepository;

@Service
public class MagazineService {
	
	@Autowired
	MagazineRepository magazineRepository;
	
	public List<Magazine> getAll(){
		return magazineRepository.findAll();
	}
	
	public void addMagazine(Magazine s) {
		magazineRepository.save(s);
	}
	
	public void updateMagazine(Magazine s) {
		magazineRepository.save(s);
	}
	
	public Magazine getMagazine(long id) {
		return magazineRepository.getOne(id);
	}
	
	public void deleteMagazine(Long id) {
		magazineRepository.deleteById(id);
	}
	
	public void deleteAllMagazines() {
		magazineRepository.deleteAll();
	}
	
	public Boolean existsMagazine(Long id) {
		return magazineRepository.existsById(id);
	}
	
	public Magazine mapDTO(MagazineDTO magazineDto){
		
		Magazine magazine = new Magazine();
		
		magazine.setId(magazineDto.getId());
		magazine.setTitle(magazineDto.getTitle());
		magazine.setIssn(magazineDto.getIssn());
		magazine.setOpenAccess(magazineDto.getOpenaccess());
		
		return magazine;
	}
	
	public MagazineDTO mapToDTO(Magazine magazine){
		return new MagazineDTO(magazine);
	}
	
	public List<MagazineDTO> mapAllToDTO(){
		
		List<Magazine> magazines = getAll();
		List<MagazineDTO> magazineDTOs = new ArrayList<>();
		
		for(Magazine r : magazines){
			magazineDTOs.add(mapToDTO(r));
		}
		
		return magazineDTOs;
	}
}