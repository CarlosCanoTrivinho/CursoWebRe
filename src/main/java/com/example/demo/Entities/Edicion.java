package com.example.demo.Entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Edicion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private short numEdicion;
	private short plazas;
	private LocalDate fechaInicio;
	private LocalDate fechaFinalizacion;

	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;

	@ManyToOne
	@JoinColumn(name = "tutor_id")
	private Tutor tutor;
	
	@OneToMany(mappedBy = "edicion", cascade = CascadeType.ALL)
	private List<AlumnoEdicion> inscripciones;
	
}
