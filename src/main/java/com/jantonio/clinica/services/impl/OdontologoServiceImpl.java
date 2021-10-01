package com.jantonio.clinica.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jantonio.clinica.models.Odontologo;
import com.jantonio.clinica.models.dto.OdontologoDTO;
import com.jantonio.clinica.models.dto.converters.OdontologoConverter;
import com.jantonio.clinica.repository.OdontologoRepository;
import com.jantonio.clinica.services.OdontologoService;

@Service
public class OdontologoServiceImpl implements OdontologoService{
	@Autowired
	private OdontologoConverter converter;
	
	@Autowired
	private OdontologoRepository odontologoRepository;

	@Override
	public List<OdontologoDTO> listar() {
		return converter.odontologoToOdontologoDTO(odontologoRepository.findAll());
	}

	@Override
	public Optional<OdontologoDTO> buscar(Long id) {
		Optional<Odontologo> odontologo = odontologoRepository.findById(id);
		return Optional.of(converter.odontologoToOdontologoDTO(odontologo.get()));
	}

	@Override
	public OdontologoDTO crear(OdontologoDTO odontologodto) {
		Odontologo odontologo = converter.odontologodtoToOdontologo(odontologodto);
		return converter.odontologoToOdontologoDTO(odontologoRepository.save(odontologo));
	}

	@Override
	public void eliminar(Long id) {
		odontologoRepository.deleteById(id);
	}

	@Override
	public OdontologoDTO editar(OdontologoDTO odontologodto) {
		Odontologo odontologo = converter.odontologodtoToOdontologo(odontologodto);
		return converter.odontologoToOdontologoDTO(odontologoRepository.save(odontologo));
	}
}
