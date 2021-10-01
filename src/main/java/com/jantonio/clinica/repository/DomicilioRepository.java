package com.jantonio.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jantonio.clinica.models.Domicilio;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio,Long>{

}
