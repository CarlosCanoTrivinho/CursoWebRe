package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entities.Edicion;
import com.example.demo.Mappers.EdicionMapper;
import com.example.demo.Repositories.EdicionRepository;
import com.example.demo.dtos.EdicionDTO;

@Service
public class EdicionService {
	
	private final EdicionRepository edicionRepository;
	private final EdicionMapper edicionMapper;
	

	public EdicionService(EdicionRepository edicionRepository, EdicionMapper edicionMapper) {
		super();
		this.edicionRepository = edicionRepository;
		this.edicionMapper = edicionMapper;
	}


	public List<EdicionDTO> cancelEditionOnFailure(LocalDate fecha) {
			List<Edicion> edicionesActivas = edicionRepository.findByFechaInicioLessThanEqualAndFechaFinalizacionGreaterThanEqual(fecha, fecha);
			edicionesActivas.forEach(Edicion::cancel);
			edicionRepository.saveAll(edicionesActivas);
			return edicionesActivas.stream().map(edicionMapper::mapToDTO).toList();	
	}
}
