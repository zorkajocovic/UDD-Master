package com.example.NaucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NaucnaCentrala.model.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long> {

}