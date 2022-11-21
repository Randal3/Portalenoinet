package com.Portalenoinet.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Portalenoinet.model.Sim;
import com.Portalenoinet.repository.SimRepository;

@Service
public class SimService {
    
	@Autowired
    protected SimRepository simRepository;

    @Transactional
    public Sim getSim(long id) {
		Optional<Sim> sim = this.simRepository.findById(id);
        return sim.orElse(null);
    }

    @Transactional
    public List<Sim> all() {
        return this.simRepository.findAll();
    }

    public void save(Sim nuovaSim) {
        this.simRepository.save(nuovaSim);
    }
}
