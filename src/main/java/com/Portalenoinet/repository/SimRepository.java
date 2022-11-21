package com.Portalenoinet.repository;

import org.springframework.data.repository.CrudRepository;
import com.Portalenoinet.model.Sim;

import java.util.List;

public interface SimRepository extends CrudRepository<Sim, Long> {
    
    public List<Sim> findAll();

}
