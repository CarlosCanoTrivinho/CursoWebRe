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
public class AlumnoEdicion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String situacion;

	 @ManyToOne
	 @JoinColumn(name = "alumno_id")
	 private Alumno alumno;

	@OneToMany(mappedBy = "alumnoEdicion", cascade = CascadeType.ALL)
	private List<ElementoEvaluado> elementos;

	@ManyToOne
	@JoinColumn(name = "edicion_id")
	private Edicion edicion;
}
