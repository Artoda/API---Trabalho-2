package com.atividade1.Atividade1.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade1.Atividade1.dto.TurmaDTO;
import com.atividade1.Atividade1.entities.Turma;
import com.atividade1.Atividade1.repositories.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;

	public List<Turma> getAllTurmas() { 
		return turmaRepository.findAll();
	}
	
	public List<TurmaDTO> getAllTurmasDTO() { 
		ModelMapper modelMapper = new ModelMapper();
		
		List<TurmaDTO> turmaDto = new ArrayList<>();
		for(Turma turma: turmaRepository.findAll()) {
			TurmaDTO novaTurmaDto = new TurmaDTO();
			
			novaTurmaDto.setNomeDisciplina(turma.getNome_disciplina());
			novaTurmaDto.setDiaSemana(turma.getDia_semana());
			novaTurmaDto.setNomeInstrutor(turma.getInstrutor().getNome());
			
			turmaDto.add(novaTurmaDto);
		}
		
		return turmaDto;
	}
	
	public Turma getTurmaById(Integer id) {
		return turmaRepository.findById(id).orElse(null);
	}
	
	public Turma saveTurma(Turma turma) { 
		return turmaRepository.save(turma); 
	}
	
	public Turma updateTurma(Turma turma, Integer id) { 
		return turmaRepository.save(turma);
	}

	public Boolean delTurma(Integer id) {
		Turma turma = turmaRepository.findById(id).orElse(null);
		
		if (turma != null) {
			turmaRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
