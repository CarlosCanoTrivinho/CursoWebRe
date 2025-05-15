package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entities.AlumnoEdicion;
import com.example.demo.Entities.Edicion;
import com.example.demo.POJO.Situacion;
import com.example.demo.Repositories.AlumnoEdicionRepository;
import com.example.demo.Repositories.EdicionRepository;

@SpringBootTest
class CancelandoEdicionesFallidasTest {
	@Autowired
	EdicionRepository edicionRepository;
	@Autowired
	AlumnoEdicionRepository alumnoEdicionRepository;
	
	@Test
	void test() {
		//100% de plazas ocupadas
		//100% tasa de abandono
		Optional<AlumnoEdicion> alumnoEdicionById = alumnoEdicionRepository.findById(2L);
		Optional<Edicion> edicionById = edicionRepository.findById(2L);
		Edicion edicion= edicionById.get();
		long count = alumnoEdicionById.stream().filter(ae -> ae.getSituacion().equals(Situacion.Desmatriculado)).count();
		edicion.cancel();
		assertFalse(edicion.isAprobado());
		
		
		
	}

}
