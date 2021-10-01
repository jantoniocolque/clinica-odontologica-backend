package com.jantonio.clinica.models.dto.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jantonio.clinica.models.Odontologo;
import com.jantonio.clinica.models.dto.OdontologoDTO;

@Component
public class OdontologoConverter {
	@Autowired
    ObjectMapper mapper;
	
	public OdontologoDTO odontologoToOdontologoDTO(Odontologo odontologo) {
		OdontologoDTO odontologodto = mapper.convertValue(odontologo, OdontologoDTO.class);
		return odontologodto;
	}
	
	public List<OdontologoDTO> odontologoToOdontologoDTO(List<Odontologo> odontologos){
		List<OdontologoDTO> odontologosdto = new ArrayList<OdontologoDTO>();
		for(Odontologo odontologo: odontologos) {
			odontologosdto.add(odontologoToOdontologoDTO(odontologo));
		}
		return odontologosdto;
	}
	
	public List<Odontologo> odontologodtoToOdontologo(List<OdontologoDTO> odontologosdto){
		List<Odontologo> pacientes = new ArrayList<Odontologo>();
		for(OdontologoDTO odontologodto: odontologosdto) {
			pacientes.add(odontologodtoToOdontologo(odontologodto));
		}
		return pacientes;
	}
	
	public Odontologo odontologodtoToOdontologo(OdontologoDTO odontologodto) {
		Odontologo odontologo = mapper.convertValue(odontologodto, Odontologo.class);
		return odontologo;
	}
}
