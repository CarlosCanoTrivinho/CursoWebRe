package com.example.demo.Entities;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.POJO.Situacion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	private boolean aprobado=true;
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;

	@ManyToOne
	@JoinColumn(name = "tutor_id")
	private Tutor tutor;
	
	@OneToMany(mappedBy = "edicion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<AlumnoEdicion> inscripciones;

	public void cancel() {
		if(plazas<inscripciones.size()*2 && isBiggerThanPercentage(70)) {
			aprobado=false;
		}
		
	}
	
	private long cuentaBajas() {
		long count = inscripciones.stream().filter(inscripcion-> !inscripcion.getSituacion().equals(Situacion.Matriculado)).count();
		return count;
	}
	
	private boolean isBiggerThanPercentage(float percentage) {
		return ((float)cuentaBajas()*100)/inscripciones.size()>=percentage;
	}
	
}
