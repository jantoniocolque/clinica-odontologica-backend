package com.jantonio.clinica.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name ="pacientes")
@ToString
public class Paciente {
	
	@Id
    @SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_sequence")
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
    private String nombre;
	
	@Getter @Setter
    private String apellido;
	
	@Getter @Setter
    private String dni;
	
	@Getter @Setter
    private Date fechaIngreso;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_domicilios",referencedColumnName = "id")
	@Getter @Setter
    private Domicilio domicilio;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_odontologo")
	@Getter @Setter
	@ToString.Exclude
    private Odontologo odontologo;*/

    public Paciente() {
    }

    public Paciente(Long id, String nombre, String apellido, String dni, Date fechaIngreso, Domicilio domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public Paciente(String nombre, String apellido, String dni, Date fechaIngreso, Domicilio domicilio) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        
    }
    
    
    
}
