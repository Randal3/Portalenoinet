package com.Portalenoinet.repository;

import org.springframework.data.repository.CrudRepository;

import com.Portalenoinet.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {
	
}