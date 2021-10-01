package com.jantonio.clinica.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jantonio.clinica.models.Usuario;
import com.jantonio.clinica.models.dto.UsuarioDTO;
import com.jantonio.clinica.models.dto.converters.UsuarioConverter;
import com.jantonio.clinica.repository.UsuarioRepository;
import com.jantonio.clinica.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService{
	@Autowired
    private UsuarioConverter converter;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Override
	public UsuarioDTO crear(UsuarioDTO usuariodto) {
		Usuario usuario = converter.usuariodtoToUsuario(usuariodto);
		return converter.usuarioToUsuarioDTO(usuarioRepository.save(usuario));
	}

	@Override
	public List<UsuarioDTO> listar() {
		return null;
	}

	@Override
	public UsuarioDTO login(Usuario usuario) {
		Boolean existe = usuarioRepository.findByEmail(usuario.getEmail()).isPresent();
		Usuario usuarioLocal = null;
		
		if(existe) {
			usuarioLocal = usuarioRepository.findByEmail(usuario.getEmail()).get();
			if(bCryptPasswordEncoder.matches(usuario.getContrasenia(), usuarioLocal.getContrasenia()))
			{
				return converter.usuarioToUsuarioDTO(usuarioLocal);
			}
		}
		
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return usuarioRepository.findByEmail(email).orElseThrow((() -> new UsernameNotFoundException("User email not found")));
		
	}
	
}
