package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entities.Curso;
import com.example.demo.Repositories.CursoRepository;

@SpringBootTest
class PaginaCursosReApplicationTests {
	@Autowired
	CursoRepository cursoRepository;
	@Test
	void contextLoads() {
		Curso curso=new Curso();
		cursoRepository.save(curso);
	}

}
