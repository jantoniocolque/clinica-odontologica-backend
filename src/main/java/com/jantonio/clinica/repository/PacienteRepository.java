package com.jantonio.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jantonio.clinica.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
