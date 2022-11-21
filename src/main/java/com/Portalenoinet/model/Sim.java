package com.Portalenoinet.model;

import java.sql.Date;

import javax.persistence.CascadeType;
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
	private Date data;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Utente operatore;

    @Column(nullable = false)
	private String stato;

	@Column(nullable = false)
	private String tipoServizio;

	@Column(nullable = false)
	private String areaCode;

	@Column
	private String dettagli;
}
