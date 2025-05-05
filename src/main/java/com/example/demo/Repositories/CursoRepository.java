package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.Curso;


public interface CursoRepository extends JpaRepository<Curso, Long>{

}
