package com.example.demo.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.Edicion;

public interface EdicionRepository extends JpaRepository<Edicion, Long> {
	public List<Edicion> findByFechaInicioBeforeAndFechaFinalizacionAfter(LocalDate fecha, LocalDate fecha2);
	public List<Edicion> findByFechaInicioAfterOrFechaFinalizacionBefore(LocalDate fecha, LocalDate fecha2);
	public List<Edicion> findByFechaInicioBefore(LocalDate fecha);
	
	public List<Edicion> findByFechaInicioLessThanEqualAndFechaFinalizacionGreaterThanEqual(LocalDate fecha, LocalDate fecha2);
	public Optional<Edicion> findById(Long id);
}
