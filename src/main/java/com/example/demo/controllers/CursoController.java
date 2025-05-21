package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.EdicionDTO;
import com.example.demo.services.EdicionService;

@RestController
@RequestMapping("curso")
public class CursoController {
	private final EdicionService edicionService;

	public CursoController(EdicionService edicionService) {
		super();
		this.edicionService = edicionService;
	}

	@PatchMapping("cancelacion")
	public ResponseEntity<List<EdicionDTO>> cancelEditionFailed(LocalDate fecha) {
		return ResponseEntity.ok(edicionService.cancelEditionOnFailure(fecha));
	}
}
