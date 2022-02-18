package com.cefet.salao.api.security.data;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cefet.salao.api.entities.Usuario;

import java.util.ArrayList;
import java.util.Optional;

public class DetalheUsuarioData implements UserDetails{
	
	private final Optional<Usuario> usuario;
	
    public DetalheUsuarioData(Optional<Usuario> usuario) {
        this.usuario = usuario;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
	     return usuario.orElse(new Usuario()).getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.orElse(new Usuario()).getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
