package com.jantonio.clinica.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name ="pacientes")
@ToString
@Getter @Setter
public class Paciente {
	
	@Id
    @SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_sequence")
	private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaAlta;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_domicilio",referencedColumnName = "id")
    private Domicilio domicilio;
	
	@OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();
	
    public Paciente() {
    }

	public Paciente(String nombre, String apellido, String dni, LocalDate fechaAlta, Domicilio domicilio) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaAlta = fechaAlta;
		this.domicilio = domicilio;
	}
}
