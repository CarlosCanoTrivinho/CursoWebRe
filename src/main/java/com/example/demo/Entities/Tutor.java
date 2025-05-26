package com.example.demo.Entities;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.POJO.Categoria;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
	
	@OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Edicion> ediciones=new ArrayList<>();
	
	public void addEdicion(Edicion edicion) {
		ediciones.add(edicion);
	}
	public void deleteEdicion(Edicion edicion) {
		ediciones.remove(edicion);
	}
	
	
	
	
}
