package com.example.demo.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Tarea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	
	@OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL)
	private List<ElementoEvaluado> elementos;
	
	@OneToMany(mappedBy = "tarea", cascade = CascadeType.ALL)
	private List<Enunciado> enunciados;
}
