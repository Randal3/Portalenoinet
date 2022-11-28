package com.Portalenoinet.model;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Sim {
	
	@Id
	private String Seriale;

	@Column(nullable = false)
	private String idRecord;

	@Column
	private LocalDate data;
	
	@OneToOne
	private Utente operatore;

    @Column(nullable = false)
	private String stato;

	@Column
	private String tipoServizio;

	@Column
	private String areaCode;

	@Column
	private String dettagli;
}
