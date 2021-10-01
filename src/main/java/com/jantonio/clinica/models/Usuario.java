package com.jantonio.clinica.models;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="usuarios")
@ToString
@Getter @Setter
public class Usuario implements UserDetails{

	@Id
	@SequenceGenerator(name = "usuario_sequence", sequenceName = "usuario_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
	private Long id;
	
	private String email;
	
	private String contrasenia;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_paciente",referencedColumnName="id")
	private Paciente paciente;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;
	
	public Usuario() {}
	public Usuario(String string) {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tipo.name());
		return Collections.singleton(grantedAuthority);
	}

	@Override
	public String getPassword() {
		return this.contrasenia;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Usuario(String email, String contrasenia, TipoUsuario tipo) {
		this.email = email;
		this.contrasenia = contrasenia;
		this.tipo = tipo;
	}

}
