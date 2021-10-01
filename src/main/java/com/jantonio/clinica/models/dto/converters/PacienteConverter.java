package com.jantonio.clinica.models.dto.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jantonio.clinica.models.Domicilio;
import com.jantonio.clinica.models.Paciente;
import com.jantonio.clinica.models.dto.PacienteDTO;

@Component
public class PacienteConverter {
	@Autowired
    ObjectMapper mapper;
	
	public PacienteDTO pacienteToPacienteDTO(Paciente paciente) {
		PacienteDTO pacientedto = mapper.convertValue(paciente, PacienteDTO.class);
		pacientedto.setId_domicilio(paciente.getDomicilio().getId());
		pacientedto.setCalle(paciente.getDomicilio().getCalle());
		pacientedto.setNumero(paciente.getDomicilio().getNumero());
		pacientedto.setLocalidad(paciente.getDomicilio().getLocalidad());
		pacientedto.setProvincia(paciente.getDomicilio().getProvincia());
		return pacientedto;
	}
	
	public List<PacienteDTO> pacienteToPacienteDTO(List<Paciente> pacientes){
		List<PacienteDTO> pacientesdto = new ArrayList<PacienteDTO>();
		for(Paciente paciente: pacientes) {
			pacientesdto.add(pacienteToPacienteDTO(paciente));
		}
		return pacientesdto;
	}
	
	public List<Paciente> pacientedtoToPaciente(List<PacienteDTO> pacientesdto){
		List<Paciente> pacientes = new ArrayList<Paciente>();
		for(PacienteDTO pacientedto: pacientesdto) {
			pacientes.add(pacientedtoToPaciente(pacientedto));
		}
		return pacientes;
	}
	
	public Paciente pacientedtoToPaciente(PacienteDTO pacientedto) {
		Paciente paciente = mapper.convertValue(pacientedto, Paciente.class);
		Domicilio domicilio = new Domicilio();
		domicilio.setId(pacientedto.getId_domicilio());
		domicilio.setCalle(pacientedto.getCalle());
		domicilio.setNumero(pacientedto.getNumero());
		domicilio.setLocalidad(pacientedto.getLocalidad());
		domicilio.setProvincia(pacientedto.getProvincia());
		paciente.setDomicilio(domicilio);
		return paciente;
	}
}
