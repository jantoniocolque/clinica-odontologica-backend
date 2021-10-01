package com.jantonio.clinica.models.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PacienteDTO {
	private Long id;
	private String nombre;
	private String apellido;
	private String dni;
	private LocalDate fechaAlta;
	private Long id_domicilio;
	private String calle;
	private Integer numero;
	private String localidad;
	private String provincia;
}
