package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dtos.EdicionDTO;
import com.example.demo.services.EdicionService;

@SpringBootTest
class probandoEdicionService {
	@Autowired
	EdicionService edicionService;

	@Test
	void test() {
		LocalDate fecha=LocalDate.of(2025, 8, 1);
		List<EdicionDTO> cancelEditionOnFailure = edicionService.cancelEditionOnFailure(fecha);
		assertEquals(cancelEditionOnFailure.size(), 24);
		List<EdicionDTO> cancelEditionOnFailureAndCancelled=edicionService.getEdicionesActivesAndCancelled(fecha);
		assertEquals(cancelEditionOnFailureAndCancelled.size(), 3);
	}

}
