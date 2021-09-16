package com.jantonio.clinica.services;

import java.util.List;
import java.util.Optional;

import com.jantonio.clinica.models.Paciente;

public interface PacienteService {
	List<Paciente> listar();
	Optional<Paciente> buscar(Long id);
	Paciente crear(Paciente paciente);
    void eliminar(Long id);
    Paciente editar(Paciente paciente);
}
