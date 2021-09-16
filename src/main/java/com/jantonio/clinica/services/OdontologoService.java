package com.jantonio.clinica.services;

import java.util.List;
import java.util.Optional;

import com.jantonio.clinica.models.Odontologo;


public interface OdontologoService {
	List<Odontologo> listar();
	Optional<Odontologo> buscar(Long id);
    Odontologo crear(Odontologo odontologo);
    void eliminar(Long id);
    Odontologo editar(Odontologo odontologo);
}
