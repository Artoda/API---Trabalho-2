package com.atividade1.Atividade1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade1.Atividade1.dto.InstrutorDTO;
import com.atividade1.Atividade1.entities.Instrutor;
import com.atividade1.Atividade1.repositories.InstrutorRepository;

@Service
public class InstrutorService {

	@Autowired
	private InstrutorRepository instrutorRepository;
	
	@Autowired 
	EmailService emailService;
	
	public List<Instrutor> getAllInstrutors() {
		return instrutorRepository.findAll();
	}
	
	public List<InstrutorDTO> getAllInstrutorsDTO() { 
		ModelMapper modelMapper = new ModelMapper();
		
		List<InstrutorDTO> instrutoresDTO = new ArrayList<>();
		
		for(Instrutor instrutor: instrutorRepository.findAll()) {
			InstrutorDTO novoInstDto = new InstrutorDTO();
			novoInstDto.setNome(instrutor.getNome());
			
			if (instrutor.getTelefone() != null)
				novoInstDto.setTelefone(instrutor.getTelefone().getNumero());
			else
				novoInstDto.setTelefone(null);
			
			instrutoresDTO.add(novoInstDto);		
		}
		
		return instrutoresDTO;
	}
	
	public Instrutor getInstrutorById(Integer id) {
		ModelMapper modelMapper = new ModelMapper();
		Optional<Instrutor> novoInstrutor= instrutorRepository.findById(id);
		Instrutor instrutorEmail = modelMapper.map(novoInstrutor, Instrutor.class);
		
		emailService.enviarEmail("romuloandriolo@hotmail.com", "Novo instrutor cadastrado!", instrutorEmail.toStringGet());
		
		return instrutorRepository.findById(id).orElse(null);
	}
	
	public Instrutor saveInstrutor(Instrutor instrutor) { 
		Instrutor novoInstrutor= instrutorRepository.save(instrutor);
		emailService.enviarEmail("romuloandriolo@hotmail.com", "Novo instrutor cadastrado!", novoInstrutor.toStringSave());
		return novoInstrutor;
	}
	
	public Instrutor updateInstrutor(Instrutor instrutor, Integer id) { 
		return instrutorRepository.save(instrutor);
	}

	public Boolean delInstrutor(Integer id) {
		Instrutor instrutor = instrutorRepository.findById(id).orElse(null);
		
		if (instrutor != null) {
			instrutorRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
