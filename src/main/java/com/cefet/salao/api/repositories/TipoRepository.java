package com.cefet.salao.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cefet.salao.api.entities.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long>{
}
