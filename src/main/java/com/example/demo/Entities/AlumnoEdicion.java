package com.example.demo.Entities;

import java.util.List;

import com.example.demo.POJO.Situacion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	@Enumerated(EnumType.STRING)
	private Situacion situacion;

	 @ManyToOne
	 @JoinColumn(name = "alumno_id")
	 private Alumno alumno;

	@OneToMany(mappedBy = "alumnoEdicion", cascade = CascadeType.ALL)
	private List<ElementoEvaluado> elementos;

	@ManyToOne
	@JoinColumn(name = "edicion_id")
	private Edicion edicion;
}
