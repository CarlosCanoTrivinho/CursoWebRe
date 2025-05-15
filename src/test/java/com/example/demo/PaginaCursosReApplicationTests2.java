package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entities.Edicion;
import com.example.demo.Repositories.EdicionRepository;

@SpringBootTest(classes = PaginaCursosReApplication.class)
class PaginaCursosReApplicationTests2 {
//	@Autowired
//	CursoRepository cursoRepository;
	@Autowired
	EdicionRepository edicionRepository;
//	@Test
//	void contextLoads() {
//		Curso curso=new Curso();
//		cursoRepository.saveAndFlush(curso);
//	}

	@Test
	void testEdicionFechaActual() {
		List<Edicion> all = edicionRepository.findAll();
		Optional<LocalDate> min = all.stream().map(edicion -> edicion.getFechaInicio()).min(LocalDate::compareTo);
		LocalDate inicio = min.get();
		List<Edicion> ediciones = edicionRepository.findByFechaInicioLessThanEqualAndFechaFinalizacionGreaterThanEqual(inicio, inicio);
		assertTrue(ediciones.size() > 0);
		assertTrue(all.size() > 0);

		List<Edicion> inactivos = edicionRepository.findByFechaInicioAfterOrFechaFinalizacionBefore(inicio, inicio);
		assertEquals(inactivos.size() + ediciones.size(), all.size());

//		LocalDate inicio = LocalDate.of(2025, 6, 7);
//		long countIinico = all.stream().filter(edicion -> edicion.getFechaInicio().equals(inicio)).count();
//		assertTrue(countIinico > 0);
//
//		LocalDate finalizacion = LocalDate.of(2025, 10, 7);
//		long countFinalizacion = all.stream().filter(edicion -> edicion.getFechaFinalizacion().equals(finalizacion))
//				.count();
//		assertTrue(countFinalizacion > 0);
//
//		Edicion edicion = activos.get(0);
//
//		List<Edicion> fechaEstrictaInicio = edicionRepository
//				.findByFechaInicioBeforeAndFechaFinalizacionAfter(edicion.getFechaInicio(), edicion.getFechaInicio());
//		activos.contains(fechaEstrictaInicio);
//
//		List<Edicion> fechasEstrictas = fechaEstrictaInicio.stream()
//				.filter(fecha -> activos.stream()
//						.filter(edicionEstricta -> edicionEstricta.getFechaInicio().equals(fechaEstrictaInicio))
//						.findFirst().isEmpty())
//				.collect(Collectors.toList());
//
//		assertTrue(fechasEstrictas.size() == 0);
	}

	@Test
	void testInicioEstricto() {
		List<Edicion> all = edicionRepository.findAll();
		Optional<LocalDate> min = all.stream().map(edicion -> edicion.getFechaInicio()).min(LocalDate::compareTo);
		LocalDate inicio = min.get();
		List<Edicion> byFechaInicioBefore = edicionRepository.findByFechaInicioBefore(inicio);
		assertTrue(
				byFechaInicioBefore.stream().allMatch(edicion -> edicion.getFechaInicio().equals(byFechaInicioBefore)));

		assertFalse(byFechaInicioBefore.size() == all.size());

		System.out.println("TOTALES DE FECHAS----------------------------------" + byFechaInicioBefore.size());

	}

}
