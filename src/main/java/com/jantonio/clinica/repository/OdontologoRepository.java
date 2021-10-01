package com.jantonio.clinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jantonio.clinica.models.Odontologo;


@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long>{
	List<Odontologo> findByNombreContaining(String nombre);
}
