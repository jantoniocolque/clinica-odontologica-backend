package com.jantonio.clinica.services;

import java.util.List;
import java.util.Optional;

import com.jantonio.clinica.models.Domicilio;


public interface DomicilioService {
	List<Domicilio> listar();
	Optional<Domicilio> buscar(Long id);
	Domicilio crear(Domicilio domicilio);
    void eliminar(Long id);
    Domicilio editar(Domicilio domicilio);
}
