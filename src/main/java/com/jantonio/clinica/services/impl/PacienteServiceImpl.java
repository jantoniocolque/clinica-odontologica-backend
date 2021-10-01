package com.jantonio.clinica.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jantonio.clinica.models.Paciente;
import com.jantonio.clinica.models.dto.PacienteDTO;
import com.jantonio.clinica.models.dto.converters.PacienteConverter;
import com.jantonio.clinica.repository.PacienteRepository;
import com.jantonio.clinica.services.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService{
	@Autowired
    private PacienteConverter converter;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	
	@Override
	public List<PacienteDTO> listar() {
		return converter.pacienteToPacienteDTO(pacienteRepository.findAll());
	}

	@Override
	public Optional<PacienteDTO> buscar(Long id) throws Exception {
		Optional<Paciente> paciente = pacienteRepository.findById(id);
		if(paciente.isPresent())
			return Optional.of(converter.pacienteToPacienteDTO(paciente.get()));
		else
			throw new Exception("Paciente no existe");
	}

	@Override
	public PacienteDTO crear(PacienteDTO pacientedto) {
		Paciente paciente = converter.pacientedtoToPaciente(pacientedto);
		return converter.pacienteToPacienteDTO(pacienteRepository.save(paciente));
	}

	@Override
	public void eliminar(Long id) {
		pacienteRepository.deleteById(id);
	}

	@Override
	public PacienteDTO editar(PacienteDTO pacientedto) {
		Paciente paciente = converter.pacientedtoToPaciente(pacientedto);
		return converter.pacienteToPacienteDTO(pacienteRepository.save(paciente));
	}


	@Override
	public List<PacienteDTO> buscarPorFechaAlta(LocalDate fecha) {
		return converter.pacienteToPacienteDTO(pacienteRepository.findByFechaAlta(fecha));
	}

}
