package com.jantonio.clinica.models.dto;

import org.springframework.stereotype.Component;

import com.jantonio.clinica.models.TipoUsuario;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter @Setter
public class UsuarioDTO {
	private Long id;
	private String email;
	private String contrasenia;
	private TipoUsuario tipo;
	private Long id_paciente;
	private String nombre;
	private String apellido;
	private String dni;
	private Long id_domicilio;
	private String calle;
	private Integer numero;
	private String localidad;
	private String provincia;
}
