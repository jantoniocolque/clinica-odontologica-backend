package com.jantonio.clinica.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.jantonio.clinica.models.Turno;
import com.jantonio.clinica.models.dto.TurnoDTO;

public interface TurnoService {
	List<Turno> listar();
	Optional<Turno> buscar(Long id);
	Turno crear(TurnoDTO turnodto);
    void eliminar(Long id);
    Turno editar(TurnoDTO turnodto);
    List<Turno> buscarPorOdontologo(Long id);
    List<Turno> buscarPorPaciente(Long id);
    List<Turno> turnosEntreFechas(LocalDate fechaInicial,LocalDate fechaFinal);
    List<Turno> turnoPorFecha(LocalDate fecha);
    List<Turno> buscarPorNombreOdontologo(String nombre);
    List<Turno> buscarPorNombrePaciente(String nombre);
}
