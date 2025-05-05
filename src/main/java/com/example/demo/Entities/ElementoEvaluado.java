package com.example.demo.Entities;

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
public class ElementoEvaluado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String observaciones;
	private double nota;
	
	@ManyToOne
	@JoinColumn(name="alumnoEdicion_id")
	private AlumnoEdicion alumnoEdicion;
	
	@ManyToOne
	@JoinColumn(name="tarea_id")
	private Tarea tarea;
	
	@OneToMany(mappedBy = "elementoEvaluado", cascade = CascadeType.ALL)
	private List<Respuesta> respuestas;
	
	
}
