package com.jantonio.clinica.repository.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.jantonio.clinica.models.TipoUsuario;
import com.jantonio.clinica.models.Usuario;
import com.jantonio.clinica.repository.UsuarioRepository;

@Component
public class DataLoader implements ApplicationRunner{
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
		String password = passwordEncoder.encode("antares123");
		//String password2 = passwordEncoder.encode("antares1234");
		usuarioRepository.save(new Usuario("antares@odontologica.com",password,TipoUsuario.ADMINISTRADOR));
		//usuarioRepository.save(new Usuario("jefe@gmail.com",password2,TipoUsuario.INVITADO));
	}

}
