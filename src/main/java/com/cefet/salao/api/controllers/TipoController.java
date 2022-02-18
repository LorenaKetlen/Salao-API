package com.cefet.salao.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.annotations.Api;

import com.cefet.salao.api.entities.Tipo;
import com.cefet.salao.api.services.TipoService;

@RestController
@RequestMapping("/api/tipos")
@Api(value = "tipos", tags = "Tipos")
@CrossOrigin(origins = "*")
public class TipoController {

	@Autowired
	private TipoService tipoService;
	
	public TipoController() {}

    @PostMapping("/")
    public ResponseEntity<Tipo> createTipo(@Valid @RequestBody Tipo tipo) throws URISyntaxException {
        if (tipo.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo tipo não pode ter um ID");
        }
        Tipo result = tipoService.save(tipo);
        return ResponseEntity.created(new URI("/api/tipos/" + result.getId()))
                .body(result);
    }

    @PutMapping("/")
    public ResponseEntity<Tipo> updateTipo(@Valid @RequestBody Tipo tipo) throws URISyntaxException {
        if (tipo.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid tipo id null");
        }
        Tipo result = tipoService.save(tipo);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo> getContato(@PathVariable Long id) {
        Optional<Tipo> tipo = tipoService.findOne(id);
        if(tipo.isPresent()) {
            return ResponseEntity.ok().body(tipo.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Tipo>> getTipo(){
       List<Tipo> lista = tipoService.findAllList();
       
       return ResponseEntity.ok().body(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipo(@PathVariable Long id) {
        tipoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
