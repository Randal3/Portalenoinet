package com.Portalenoinet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.Portalenoinet.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {

    public List<Utente> findAll();
	
}