package com.Portalenoinet.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.Portalenoinet.model.Sim;

import java.util.List;
import java.util.Optional;

public interface SimRepository extends CrudRepository<Sim, Long> {
    
    public List<Sim> findAll();

    @Query(value = "select * from Sim where seriale=?1 ", nativeQuery = true)
	Optional<Sim> findBySeriale(String seriale);
}
