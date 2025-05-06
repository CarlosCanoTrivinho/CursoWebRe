package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.POJO.Categoria;
import com.example.demo.Entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	List<Curso> findActiveCourses();
	List<Curso> findByCategoria(Categoria categoria);
	
}
