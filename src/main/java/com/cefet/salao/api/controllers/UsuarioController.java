package com.cefet.salao.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cefet.salao.api.entities.Usuario;
import com.cefet.salao.api.services.UsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.annotations.Api;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api/usuario")
@Api(value = "usuario", tags = "Usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PasswordEncoder encoder;
	
	public UsuarioController() {}

    @PostMapping("/")
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario) throws URISyntaxException {
        if (usuario.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo usuario não pode ter um ID");
        }

        usuario.setAdmin(false);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        Usuario result = usuarioService.save(usuario);
        return ResponseEntity.created(new URI("/api/usuario/" + result.getId()))
                .body(result);
    }

    @PutMapping("/")
    public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody Usuario usuario) throws URISyntaxException {
        if (usuario.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid usuario id null");
        }

        Usuario result = usuarioService.save(usuario);
        return ResponseEntity.ok()
                .body(result);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<Usuario> saveAndUpdatePassword(@Valid @RequestBody Usuario usuario) throws URISyntaxException {
        if (usuario.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid usuario id null");
        }

        usuario.setSenha(encoder.encode(usuario.getSenha()));        
        Usuario result = usuarioService.save(usuario);
        return ResponseEntity.ok()
                .body(result);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findOne(id);
        if(usuario.isPresent()) {
            return ResponseEntity.ok().body(usuario.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getUsuario(){
       List<Usuario> lista = usuarioService.findAllList();
       return ResponseEntity.ok().body(lista);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}/exists")
    public ResponseEntity<Boolean> isExisting(@PathVariable String email){
        if(usuarioService.findByEmail(email).isPresent()) {
            return ResponseEntity.ok().body(Boolean.TRUE);
        }else{
        	return ResponseEntity.ok().body(Boolean.FALSE);
        }
    }
    
    @GetMapping("/{email}/{senha}/authenticate")
    public ResponseEntity<Usuario> authenticateUsuario(@PathVariable  String email, @PathVariable String senha){
        Optional<Usuario> usuario = usuarioService.findUsuarioByEmailAndSenha(email, encoder.encode(senha));

        if(usuario.isPresent()) {
            return ResponseEntity.ok().body(usuario.get());
        }else{
        	return ResponseEntity.ok().body(new Usuario());
        }

    }
    
    @GetMapping("/emailAuthenticate")
    public String usuarioAuthenticate(Authentication authentication){
        return authentication.getName();
    }
    
    @GetMapping("/{email}/authenticate")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String email) {
        Optional<Usuario> usuario = usuarioService.findByEmail(email);
        if(usuario.isPresent()) {
            return ResponseEntity.ok().body(usuario.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
}
