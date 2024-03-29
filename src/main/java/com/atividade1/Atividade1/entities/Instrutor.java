package com.atividade1.Atividade1.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id_instrutor"
		) 
@Entity
public class Instrutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_instrutor")
	private Integer id_instrutor;
	
	@Column(name = "rg")
	@NotNull
	private Integer rg;
	
	@Column(name = "nome")
	@NotBlank
	private String nome;

	@OneToOne(mappedBy = "instrutor")
	private Telefone telefone;
	
	@OneToMany(mappedBy = "instrutor")
	private List<Turma> turmas;
	
	
	
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Integer getId_instrutor() {
		return id_instrutor;
	}

	public void setId_instrutor(Integer id_instrutor) {
		this.id_instrutor = id_instrutor;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toStringSave() {
		return "Instrutor cadastrado com sucesso!\n"
				+ "=================================\n"
				+ "ID: " + id_instrutor + "\n" +
				"RG: " + rg + "\n" +
				"Nome: " + nome + "\n";
	}
	
	public String toStringGet() {
		return "DADOS DO INSTRUTOR\n"
				+ "=================================\n"
				+ "ID: " + id_instrutor + "\n" +
				"RG: " + rg + "\n" +
				"Nome: " + nome + "\n" +
				"Telefone: " + ((telefone == null) ? "não cadastrado" : telefone.getNumero()) + "\n" +
				"Total de turmas: " + (turmas.size());
	}
	
}
