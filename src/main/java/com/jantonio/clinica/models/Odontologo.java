package com.jantonio.clinica.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="odontologos")
@ToString
public class Odontologo {
	
	@Id
    @SequenceGenerator(name = "odontologo_sequence", sequenceName = "odontologo_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence")
	@Getter @Setter
	private Long id;
	@Getter @Setter
    private String nombre;
	@Getter @Setter
    private String apellido;
	@Getter @Setter
    private Integer matricula;

    public Odontologo() {
    }

    public Odontologo(String nombre, String apellido, Integer matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public Odontologo(Long id, String nombre, String apellido, Integer matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
        /*this.pacientes = pacientes;*/
    }
    /*
    @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Getter @Setter
    private Set<Paciente> pacientes = new HashSet<>();
    */
}
