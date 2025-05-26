package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.EdicionService;

@RestController
@RequestMapping("edicion")
public class EdicionesController {
	private final EdicionService edicionService;

	public EdicionesController(EdicionService edicionService) {
		super();
		this.edicionService = edicionService;
	}
	
	@PatchMapping("cambio")
	public ResponseEntity<Boolean> changeTutor(@RequestParam long idTutor,@RequestParam long idEdicion) {
		return ResponseEntity.ok(edicionService.assignTutor(idTutor, idEdicion));
		
	}

}
