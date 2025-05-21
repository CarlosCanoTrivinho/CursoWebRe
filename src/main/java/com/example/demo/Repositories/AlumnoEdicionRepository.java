package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.AlumnoEdicion;
import com.example.demo.Entities.Edicion;


public interface AlumnoEdicionRepository extends JpaRepository<AlumnoEdicion, Long>{
public List<AlumnoEdicion> findByEdicion(Edicion edicion);
}
