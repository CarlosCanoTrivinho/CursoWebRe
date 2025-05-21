package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entities.Edicion;
import com.example.demo.populaters.Populaters;

@SpringBootTest
class ProbandoPopulateTest {
	@Autowired
	Populaters populaters;
	@Test
	void test() {
		List<Edicion> ediciones = populaters.getEdiciones();
		assertEquals(24, ediciones.size());
		//tasa de abandono alta y además sin cubrir plazas
		Edicion edicion = ediciones.stream().filter(e->e.getId()==21L).findFirst().get();
		edicion.cancelIfConditions();
		assertFalse(edicion.isAprobado());
		//tasa de abandono baja y con plazas cubiertas
		Edicion edicionDos = ediciones.stream().filter(e->e.getId()==22L).findFirst().get();
		edicionDos.cancelIfConditions();
		assertTrue(edicionDos.isAprobado());
		//tasa de abandono baja y sin cubrir plazas
		Edicion edicionTres = ediciones.stream().filter(e->e.getId()==23L).findFirst().get();
		edicionTres.cancelIfConditions();
		assertFalse(edicionTres.isAprobado());
		//tasa de abandono alta y con plazas cubiertas
		Edicion edicionCuatro = ediciones.stream().filter(e->e.getId()==24L).findFirst().get();
		edicionCuatro.cancelIfConditions();
		assertFalse(edicionCuatro.isAprobado());
	}

}
