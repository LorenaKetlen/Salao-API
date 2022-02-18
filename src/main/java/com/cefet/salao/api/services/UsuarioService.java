package com.cefet.salao.api.services;

import java.util.Optional;
import java.util.List;

import com.cefet.salao.api.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.salao.api.entities.Usuario;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario saveAndUpdatePassword(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Optional<Usuario> findOne(Long id) {
		return usuarioRepository.findById(id);
	}
	
	public List<Usuario> findAllList() {
		return usuarioRepository.findAll();
	}
	
	public void delete(Long id) {
		usuarioRepository.deleteById(id);
	}	
	
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
    public Optional<Usuario> findUsuarioByEmailAndSenha(String email, String senha) {
        return usuarioRepository.findUsuarioByEmailAndSenha(email, senha);
    }
    
}
