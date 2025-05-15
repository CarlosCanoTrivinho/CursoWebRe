package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entities.Edicion;
import com.example.demo.Repositories.EdicionRepository;

@SpringBootTest
class CancelandoCursosPocoOcupados {
	@Autowired
	EdicionRepository edicionRepository;
	@Test
	void test() {
		Optional<Edicion> byId = edicionRepository.findById(2L);
		Edicion edicion = byId.get();
		edicion.setPlazas((short) 6);
		edicionRepository.save(edicion);
		assertEquals(edicion.getPlazas(),edicion.getInscripciones().size()*2);
		edicion.setPlazas((short) 8);
		assertTrue(((float)edicion.getInscripciones().size()/edicion.getPlazas())<0.5f);
		edicion.setAprobado(false);
		edicionRepository.save(edicion);
		assertFalse(edicionRepository.findById(2L).get().isAprobado());
		
		

	}

}
