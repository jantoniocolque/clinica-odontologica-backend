package com.jantonio.clinica.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jantonio.clinica.models.Odontologo;
import com.jantonio.clinica.models.Paciente;
import com.jantonio.clinica.models.Turno;
import com.jantonio.clinica.models.dto.TurnoDTO;
import com.jantonio.clinica.repository.OdontologoRepository;
import com.jantonio.clinica.repository.PacienteRepository;
import com.jantonio.clinica.repository.TurnoRepository;
import com.jantonio.clinica.services.TurnoService;

@Service
public class TurnoServiceImpl implements TurnoService{
	@Autowired
    ObjectMapper mapper;
	
	@Autowired
	private TurnoRepository turnoRepository;
	@Autowired
	private OdontologoRepository odontologoRepository;
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Override
	public List<Turno> listar() {
		return turnoRepository.findAllByOrderByFechaAsc();
	}

	@Override
	public Optional<Turno> buscar(Long id) {
		return turnoRepository.findById(id);
	}

	@Override
	public Turno crear(TurnoDTO turnodto) {
		return crearObjectoTurno(turnodto);
	}
	
	public Turno crearObjectoTurno(TurnoDTO turnodto) {
		Turno turno = mapper.convertValue(turnodto,Turno.class);
		Odontologo odontologo = odontologoRepository.getById(turnodto.getId_odontologo());
		Paciente paciente = pacienteRepository.getById(turnodto.getId_paciente());
		turno.setOdontologo(odontologo);
		turno.setPaciente(paciente);
		return turnoRepository.save(turno);
	}
	
	@Override
	public Turno editar(TurnoDTO turnodto) {
		return crearObjectoTurno(turnodto);
	}
	
	@Override
	public void eliminar(Long id) {
		turnoRepository.deleteById(id);
	}

	@Override
	public List<Turno> buscarPorOdontologo(Long id) {
		return turnoRepository.findByOdontologo_IdOrderByFechaDesc(id);
	}

	@Override
	public List<Turno> buscarPorPaciente(Long id) {
		return turnoRepository.findByPaciente_IdOrderByFechaDesc(id);
	}

	@Override
	public List<Turno> turnosEntreFechas(LocalDate fechaInicial,LocalDate fechaFinal) {
		return turnoRepository.turnosEntreFechas(fechaInicial,fechaFinal);
	}

	@Override
	public List<Turno> turnoPorFecha(LocalDate fecha) {
		return turnoRepository.findByFechaOrderByFechaDesc(fecha);
	}

	@Override
	public List<Turno> buscarPorNombreOdontologo(String nombre) {
		return turnoRepository.findByOdontologo_NombreContainingOrderByFechaDesc(nombre);
	}

	@Override
	public List<Turno> buscarPorNombrePaciente(String nombre) {
		return turnoRepository.findByPaciente_NombreContainingOrderByFechaDesc(nombre);
	}

}
