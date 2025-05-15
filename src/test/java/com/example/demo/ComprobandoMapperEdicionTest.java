package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entities.Edicion;
import com.example.demo.Mappers.EdicionMapper;
import com.example.demo.Repositories.EdicionRepository;
import com.example.demo.dtos.EdicionDTO;

@SpringBootTest
class ComprobandoMapperEdicionTest {
	@Autowired
	EdicionRepository edicionRepository;
	@Autowired
	EdicionMapper edicionMapper;
	@Test
	void test() {
		 Optional<Edicion> byId = edicionRepository.findById(2L);
		if (byId.isPresent()) {
			Edicion edicion = byId.get();
			EdicionDTO mapToDTO = edicionMapper.mapToDTO(edicion);
			assertEquals(edicion.getNumEdicion(), mapToDTO.numEdicion());
			assertEquals(edicion.getCurso().getTitulo(), mapToDTO.curso().titulo());
		}
			
	}

}
