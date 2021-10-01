package com.jantonio.clinica.models.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TurnoDTO {
	private Long id;
	private Long id_odontologo;
	private Long id_paciente;
	private LocalDate fecha;
	private LocalTime hora;
}
