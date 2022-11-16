package com.Portalenoinet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Portalenoinet.model.Utente;
import com.Portalenoinet.repository.UtenteRepository;

@Service
public class UtenteService {


    @Autowired
    protected UtenteRepository utenteRepository;
    
	@Transactional
    public Utente getUtente(long id) {
		Optional<Utente> utente = this.utenteRepository.findById(id);
        return utente.orElse(null);
    }

}
