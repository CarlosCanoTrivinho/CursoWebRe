package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.Entities.Curso;
import com.example.demo.POJO.Categoria;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	List<Curso> findByCategoria(Categoria categoria);
	
}
