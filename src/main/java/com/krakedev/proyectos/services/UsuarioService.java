package com.krakedev.proyectos.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Usuario;
import com.krakedev.proyectos.repositories.UsuarioRepository;

@Service
public class UsuarioService {
		private final UsuarioRepository usuarioRepository;

		public UsuarioService(UsuarioRepository usuarioRepository) {
			super();
			this.usuarioRepository = usuarioRepository;
		}
		
		public Usuario guardar(Usuario usuario){
			String contraseniaEncriptada = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
			usuario.setPassword(contraseniaEncriptada);
			return usuarioRepository.save(usuario);
		}
		
		public boolean autentificar(String username, String password) {
			Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
			return usuarioOpt.isPresent() && BCrypt.checkpw(password, usuarioOpt.get().getPassword());
		}
}
