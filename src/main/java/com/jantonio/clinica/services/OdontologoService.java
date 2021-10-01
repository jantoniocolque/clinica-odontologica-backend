package com.jantonio.clinica.services;

import java.util.List;
import java.util.Optional;

import com.jantonio.clinica.models.dto.OdontologoDTO;


public interface OdontologoService {
	List<OdontologoDTO> listar();
	Optional<OdontologoDTO> buscar(Long id);
	OdontologoDTO crear(OdontologoDTO odontologodto);
    void eliminar(Long id);
    OdontologoDTO editar(OdontologoDTO odontologodto);
}
