package com.jantonio.clinica.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jantonio.clinica.models.Domicilio;
import com.jantonio.clinica.repository.DomicilioRepository;
import com.jantonio.clinica.services.DomicilioService;

@Service
public class DomicilioServiceImpl implements DomicilioService{

	@Autowired
	private DomicilioRepository domicilioRepository;
	@Override
	public List<Domicilio> listar() {
		return domicilioRepository.findAll();
	}

	@Override
	public Optional<Domicilio> buscar(Long id) {
		return domicilioRepository.findById(id);
	}

	@Override
	public Domicilio crear(Domicilio domicilio) {
		return domicilioRepository.save(domicilio);
	}

	@Override
	public void eliminar(Long id) {
		domicilioRepository.deleteById(id);
	}

	@Override
	public Domicilio editar(Domicilio domicilio) {
		Optional<Domicilio> d = domicilioRepository.findById(domicilio.getId());
		d.get().setCalle(domicilio.getCalle());
		d.get().setNumero(domicilio.getNumero());
		d.get().setLocalidad(domicilio.getLocalidad());
		d.get().setProvincia(domicilio.getProvincia());
		return domicilioRepository.save(d.get());
	}

}
