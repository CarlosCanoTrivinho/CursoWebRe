package com.example.demo.Mappers;

import org.mapstruct.Mapper;

import com.example.demo.Entities.Curso;
import com.example.demo.dtos.CursoDTO;

@Mapper(componentModel = "spring")
public interface CursoMapper {

	public CursoDTO mapToDTO(Curso curso);
	
}
