package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.Tutor;
import com.example.demo.POJO.Categoria;


public interface TutorRepository extends JpaRepository<Tutor, Long> {
	 List<Tutor> findByCategoria(Categoria categoria);
}
