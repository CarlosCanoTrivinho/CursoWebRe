package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.Alumno;


public interface AlumnoRepository extends JpaRepository<Alumno, Long>{

}
