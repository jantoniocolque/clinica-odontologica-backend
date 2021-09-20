package com.jantonio.clinica.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jantonio.clinica.models.Paciente;
import com.jantonio.clinica.repository.PacienteRepository;
import com.jantonio.clinica.services.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService{

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Override
	public List<Paciente> listar() {
		return pacienteRepository.findAll();
	}

	@Override
	public Optional<Paciente> buscar(Long id) {
		return pacienteRepository.findById(id);
	}

	@Override
	public Paciente crear(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}

	@Override
	public void eliminar(Long id) {
		pacienteRepository.deleteById(id);
	}

	@Override
	public Paciente editar(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}

}
