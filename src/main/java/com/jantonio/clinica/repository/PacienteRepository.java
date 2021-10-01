package com.jantonio.clinica.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jantonio.clinica.models.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	
	List<Paciente> findByNombreContaining(String nombre);
	List<Paciente> findByFechaAlta(LocalDate fecha);
	List<Paciente> findByFechaAltaIsNull();
	List<Paciente> findByFechaAltaIsNotNull();
}
