package com.jantonio.clinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jantonio.clinica.models.Usuario;
import com.jantonio.clinica.models.dto.UsuarioDTO;
import com.jantonio.clinica.services.UsuarioService;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/login")
	public ResponseEntity<UsuarioDTO> login(@RequestBody Usuario user) {
		UsuarioDTO usuario= usuarioService.login(user);
		if(usuario!=null) {
			return ResponseEntity.ok(usuario);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	@PostMapping("/register")
	public ResponseEntity<UsuarioDTO> registro(@RequestBody UsuarioDTO usuariodto){
		return ResponseEntity.ok(usuarioService.crear(usuariodto));
	}
}
