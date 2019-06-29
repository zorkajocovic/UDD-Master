package com.example.NaucnaCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NaucnaCentrala.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}