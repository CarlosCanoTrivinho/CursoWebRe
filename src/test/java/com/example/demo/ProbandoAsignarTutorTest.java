package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entities.Edicion;
import com.example.demo.Entities.Tutor;
import com.example.demo.Repositories.TutorRepository;
import com.example.demo.services.EdicionService;

@SpringBootTest
class ProbandoAsignarTutorTest {
	@Autowired
	EdicionService edicionService;
	@Autowired
	TutorRepository tutorRepository;

	Optional<Tutor> tutorById;
	Optional<Tutor> tutorByIdDos;
	Optional<Edicion> edicionById;

	@BeforeEach
	void beforeEach() {
		tutorById = tutorRepository.findById(3L);
		tutorByIdDos = tutorRepository.findById(2L);
		edicionById = edicionService.getEdicionById(17L);
	}

	@Test
	void test() {

		assertEquals(tutorById.get().getId(), edicionById.get().getTutor().getId());
		int size = tutorByIdDos.get().getEdiciones().size();

		boolean assignTutor = edicionService.assignTutor(tutorByIdDos.get().getId(), edicionById.get().getId());
		assertTrue(assignTutor);
		
		edicionById = edicionService.getEdicionById(17L);
		assertEquals(edicionById.get().getTutor().getId(), tutorByIdDos.get().getId());
		tutorByIdDos=tutorRepository.findById(tutorByIdDos.get().getId());
		int size2 = tutorByIdDos.get().getEdiciones().size();
		assertEquals(size+1,size2);

	}
	@Test
	void probandoAsignacionQueSuperaElLimite() {
		Tutor tutor = tutorRepository.findById(1L).get();
		assertEquals(tutor.getEdiciones().size(), 3);
		boolean assignTutor = edicionService.assignTutor(tutor.getId(), edicionById.get().getId());
		assertFalse(assignTutor);
		
	}
	

	@AfterEach
	void afterEach() {

		edicionService.assignTutor(tutorById.get().getId(), edicionById.get().getId());
	}

}
