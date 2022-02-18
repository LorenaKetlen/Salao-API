package com.cefet.salao.api.services;

import java.util.Optional;
import java.util.List;

import com.cefet.salao.api.repositories.TipoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.salao.api.entities.Tipo;

@Service
public class TipoService {
	@Autowired
	private TipoRepository tipoRepository;
	
	public Tipo save(Tipo tipo) {
		return tipoRepository.save(tipo);
	}
	
	public Optional<Tipo> findOne(Long id) {
		return tipoRepository.findById(id);
	}
	
	public List<Tipo> findAllList() {
		return tipoRepository.findAll();
	}
	
	public void delete(Long id) {
		tipoRepository.deleteById(id);
	}
}
