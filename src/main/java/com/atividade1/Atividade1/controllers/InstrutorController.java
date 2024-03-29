package com.atividade1.Atividade1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atividade1.Atividade1.dto.InstrutorDTO;
import com.atividade1.Atividade1.dto.MessageResponseDTO;
import com.atividade1.Atividade1.entities.Instrutor;
import com.atividade1.Atividade1.repositories.InstrutorRepository;
import com.atividade1.Atividade1.services.InstrutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {

	@Autowired
	InstrutorService instrutorService;
	
	@Autowired
	InstrutorRepository instrutorRepository;
	
	@GetMapping 
	public ResponseEntity<List<Instrutor>> getAllInstrutors() {
		return new ResponseEntity<>(instrutorService.getAllInstrutors(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Instrutor> getInstrutorById(@PathVariable Integer id) {
		Instrutor instrutorResponse = instrutorService.getInstrutorById(id);
		if (instrutorResponse == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(instrutorResponse, HttpStatus.OK);
	}
	
	@GetMapping("/dto") 
	public ResponseEntity<List<InstrutorDTO>> getAllInstrutorsDTO() {
		return new ResponseEntity<>(instrutorService.getAllInstrutorsDTO(), HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> saveInstrutor(@Valid @RequestBody Instrutor instrutor) {
		if (instrutorRepository.existsByRg(instrutor.getRg())) {
			return ResponseEntity.badRequest().body(new MessageResponseDTO("Erro: Instrutor já cadastrado!"));
		}
		instrutorService.saveInstrutor(instrutor);
		return ResponseEntity.ok(new MessageResponseDTO("Usuário registrado com sucesso!"));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity<Instrutor> updateInstrutor(@RequestBody Instrutor instrutor, Integer id) {
		return new ResponseEntity<>(instrutorService.updateInstrutor(instrutor, id), HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delInstrutor(@PathVariable Integer id) {
		Boolean instrutorResponse = instrutorService.delInstrutor(id);
		if (instrutorResponse)
			return new ResponseEntity<>(instrutorResponse, HttpStatus.OK);
		else
			return new ResponseEntity<>(instrutorResponse, HttpStatus.NOT_MODIFIED);
	}
}
