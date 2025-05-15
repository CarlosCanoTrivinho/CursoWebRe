package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entities.Edicion;
import com.example.demo.Repositories.EdicionRepository;

@SpringBootTest
class ModificandoObjetosDeLaBD {
	@Autowired
	EdicionRepository edicionRepository;

	@Test
	void test() {
		Optional<Edicion> byId = edicionRepository.findById(1L);
		Edicion edicion = byId.get();
		edicion.setPlazas((short) 10);
		edicionRepository.save(edicion);
	}

}
