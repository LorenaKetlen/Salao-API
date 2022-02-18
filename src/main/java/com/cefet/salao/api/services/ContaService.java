package com.cefet.salao.api.services;

import com.cefet.salao.api.entities.Conta;
import com.cefet.salao.api.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {
	@Autowired
	private ContaRepository contaRepository;
	
	public Conta save(Conta conta) {
		return contaRepository.save(conta);
	}
	
	public Optional<Conta> findOne(Long id) {
		return contaRepository.findById(id);
	}
	
	public List<Conta> findAllList() {
		return contaRepository.findAll();
	}
	
	public void delete(Long id) {
		contaRepository.deleteById(id);
	}
}
