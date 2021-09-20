package com.jantonio.clinica.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jantonio.clinica.models.Odontologo;
import com.jantonio.clinica.repository.OdontologoRepository;
import com.jantonio.clinica.services.OdontologoService;

@Service
public class OdontologoServiceImpl implements OdontologoService{
	
	@Autowired
	private OdontologoRepository odontologoRepository;

	@Override
	public List<Odontologo> listar() {
		return odontologoRepository.findAll();
	}

	@Override
	public Optional<Odontologo> buscar(Long id) {
		return odontologoRepository.findById(id);
	}

	@Override
	public Odontologo crear(Odontologo odontologo) {
		return odontologoRepository.save(odontologo);
	}

	@Override
	public void eliminar(Long id) {
		odontologoRepository.deleteById(id);
	}

	@Override
	public Odontologo editar(Odontologo odontologo) {
		Optional<Odontologo> o = odontologoRepository.findById(odontologo.getId());
		o.get().setNombre(odontologo.getNombre());
		o.get().setApellido(odontologo.getApellido());
		o.get().setMatricula(odontologo.getMatricula());
		//o.get().setPacientes(odontologo.getPacientes());
		return odontologoRepository.save(o.get());
	}

}