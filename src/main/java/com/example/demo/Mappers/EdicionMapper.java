package com.example.demo.Mappers;

import org.mapstruct.Mapper;

import com.example.demo.Entities.Edicion;
import com.example.demo.dtos.EdicionDTO;

@Mapper(componentModel = "spring")
public interface EdicionMapper {

	public EdicionDTO mapToDTO(Edicion edicion);

}
