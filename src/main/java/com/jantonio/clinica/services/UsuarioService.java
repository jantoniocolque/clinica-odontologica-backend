package com.jantonio.clinica.services;

import java.util.List;

import com.jantonio.clinica.models.Usuario;
import com.jantonio.clinica.models.dto.UsuarioDTO;


public interface UsuarioService {
	UsuarioDTO crear(UsuarioDTO usuariodto);
	UsuarioDTO login(Usuario usuario);
	List<UsuarioDTO> listar();
}
