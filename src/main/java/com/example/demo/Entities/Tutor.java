package com.example.demo.Entities;

import java.util.List;

import com.POJO.Categoria;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Tutor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellidos;
	private String correo;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<Edicion> ediciones;
	
}
