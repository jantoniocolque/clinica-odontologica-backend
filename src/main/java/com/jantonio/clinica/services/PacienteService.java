package com.jantonio.clinica.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.jantonio.clinica.models.dto.PacienteDTO;

public interface PacienteService {
	List<PacienteDTO> listar();
	Optional<PacienteDTO> buscar(Long id) throws Exception;
	PacienteDTO crear(PacienteDTO pacientedto);
    void eliminar(Long id);
    PacienteDTO editar(PacienteDTO pacientedto);
    List<PacienteDTO> buscarPorFechaAlta(LocalDate fecha);
}
