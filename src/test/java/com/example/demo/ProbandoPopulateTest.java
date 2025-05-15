package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		assertEquals(4, ediciones.size());
		
	}

}
