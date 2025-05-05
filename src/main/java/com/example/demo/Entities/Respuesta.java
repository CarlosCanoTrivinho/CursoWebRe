package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Respuesta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String texto;
	
	@ManyToOne
	@JoinColumn(name="enunciado_id")
	private Enunciado enunciado;
	
	@ManyToOne
	@JoinColumn(name="elementoEvaluado_id")
	private ElementoEvaluado elementoEvaluado;
	
	
}
