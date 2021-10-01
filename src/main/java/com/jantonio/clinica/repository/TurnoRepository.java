package com.jantonio.clinica.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.jantonio.clinica.models.Turno;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long>{
	
	List<Turno> findByOdontologo_IdOrderByFechaDesc(Long id);
	List<Turno> findByPaciente_IdOrderByFechaDesc(Long id);
	List<Turno> findByFechaOrderByFechaDesc(LocalDate fecha);
	@Query("Select t from Turno t where t.fecha >= ?1 and t.fecha <= ?2")
	List<Turno> turnosEntreFechas(LocalDate fechaInicial,LocalDate fechaFinal);
	
	List<Turno> findByOdontologo_NombreContainingOrderByFechaDesc(String nombre);
	List<Turno> findByPaciente_NombreContainingOrderByFechaDesc(String nombre);
	List<Turno> findAllByOrderByFechaAsc();
}
