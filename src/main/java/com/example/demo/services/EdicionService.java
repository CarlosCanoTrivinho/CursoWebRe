package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Entities.Edicion;
import com.example.demo.Entities.Tutor;
import com.example.demo.Mappers.EdicionMapper;
import com.example.demo.Repositories.EdicionRepository;
import com.example.demo.Repositories.TutorRepository;
import com.example.demo.dtos.EdicionDTO;

@Service
public class EdicionService {

	private final EdicionRepository edicionRepository;
	private final EdicionMapper edicionMapper;
	private final TutorRepository tutorRepository;

	public EdicionService(EdicionRepository edicionRepository, EdicionMapper edicionMapper,
			TutorRepository tutorRepository) {
		super();
		this.edicionRepository = edicionRepository;
		this.edicionMapper = edicionMapper;
		this.tutorRepository = tutorRepository;
	}

	public List<EdicionDTO> cancelEditionOnFailure(LocalDate fecha) {
		List<Edicion> edicionesActivas = edicionRepository
				.findByFechaInicioLessThanEqualAndFechaFinalizacionGreaterThanEqual(fecha, fecha);
		edicionesActivas.forEach(Edicion::cancelIfConditions);
		edicionRepository.saveAll(edicionesActivas);
		return edicionesActivas.stream().map(edicionMapper::mapToDTO).toList();
	}

	public List<EdicionDTO> getEdicionesActivesAndCancelled(LocalDate fecha) {
		List<Edicion> edicionesActivas = edicionRepository
				.findByFechaInicioLessThanEqualAndFechaFinalizacionGreaterThanEqual(fecha, fecha);
		return edicionesActivas.stream().filter(e -> !(e.isAprobado())).map(edicionMapper::mapToDTO).toList();
	}
	//método para asignar el tutor ( sin testear )
	public boolean assignTutor(long idTutor, long idEdicion) {
		Optional<Tutor> tutorById = tutorRepository.findById(idTutor);
		Tutor tutor = tutorById.get();
		Optional<Edicion> edicionById = edicionRepository.findById(idEdicion);
		Edicion edicion = edicionById.get();
		
		long edicionesActivas = tutor.getEdiciones().stream().filter(e -> e.isAprobado()).count();
		
		if(edicionesActivas<3) {
			Tutor currentTutor = edicion.getTutor();
			if(currentTutor!=null) {
				currentTutor.deleteEdicion(edicion);
			}
			edicion.setTutor(tutor);
			tutor.addEdicion(edicion);
			edicionRepository.save(edicion);
			return true;
		}
		return false;
	}

	public Optional<Edicion> getEdicionById(long id) {
		return edicionRepository.findById(id);
	}
}
