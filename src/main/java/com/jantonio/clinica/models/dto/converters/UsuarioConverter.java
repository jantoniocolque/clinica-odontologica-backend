package com.jantonio.clinica.models.dto.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jantonio.clinica.models.Domicilio;
import com.jantonio.clinica.models.Paciente;
import com.jantonio.clinica.models.TipoUsuario;
import com.jantonio.clinica.models.Usuario;
import com.jantonio.clinica.models.dto.UsuarioDTO;

@Component
public class UsuarioConverter {
	@Autowired
    ObjectMapper mapper;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {
		UsuarioDTO usuariodto = mapper.convertValue(usuario, UsuarioDTO.class);
		if(usuariodto.getTipo() != TipoUsuario.ADMINISTRADOR) {			
			usuariodto.setId_paciente(usuario.getPaciente().getId());
			usuariodto.setNombre(usuario.getPaciente().getNombre());
			usuariodto.setApellido(usuario.getPaciente().getApellido());
			usuariodto.setDni(usuario.getPaciente().getDni());
			usuariodto.setId_domicilio(usuario.getPaciente().getDomicilio().getId());
			usuariodto.setCalle(usuario.getPaciente().getDomicilio().getCalle());
			usuariodto.setNumero(usuario.getPaciente().getDomicilio().getNumero());
			usuariodto.setLocalidad(usuario.getPaciente().getDomicilio().getLocalidad());
			usuariodto.setProvincia(usuario.getPaciente().getDomicilio().getProvincia());
		}
		
		return usuariodto;
	}
	
	public List<UsuarioDTO> pacienteToPacienteDTO(List<Usuario> usuarios){
		List<UsuarioDTO> usuariosdto = new ArrayList<UsuarioDTO>();
		for(Usuario usuario: usuarios) {
			usuariosdto.add(usuarioToUsuarioDTO(usuario));
		}
		return usuariosdto;
	}
	
	public List<Usuario> usuariodtoToUsuario(List<UsuarioDTO> usuariosdto){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for(UsuarioDTO usuariodto: usuariosdto) {
			usuarios.add(usuariodtoToUsuario(usuariodto));
		}
		return usuarios;
	}
	
	public Usuario usuariodtoToUsuario(UsuarioDTO usuariosdto) {
		Usuario usuario = mapper.convertValue(usuariosdto, Usuario.class);
		usuario.setContrasenia(bCryptPasswordEncoder.encode(usuariosdto.getContrasenia()));
		usuario.setTipo(TipoUsuario.INVITADO);
		Paciente paciente = new Paciente();
		Domicilio domicilio = new Domicilio();
		domicilio.setId(usuariosdto.getId_domicilio());
		domicilio.setCalle(usuariosdto.getCalle());
		domicilio.setNumero(usuariosdto.getNumero());
		domicilio.setLocalidad(usuariosdto.getLocalidad());
		domicilio.setProvincia(usuariosdto.getProvincia());
		paciente.setNombre(usuariosdto.getNombre());
		paciente.setApellido(usuariosdto.getApellido());
		paciente.setDni(usuariosdto.getDni());
		paciente.setDomicilio(domicilio);
		usuario.setPaciente(paciente);
		return usuario;
	}
}
