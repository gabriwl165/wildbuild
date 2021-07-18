package com.mobilebuilds.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Habilities {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	
	private String iconHabilityHREF;
	
	@Column(columnDefinition="TEXT", length = 5000)
	private String descHability;
	
	@ManyToOne
	private Champs champs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIconHabilityHREF() {
		return iconHabilityHREF;
	}

	public void setIconHabilityHREF(String iconHabilityHREF) {
		this.iconHabilityHREF = iconHabilityHREF;
	}

	public String getDescHability() {
		return descHability;
	}

	public void setDescHability(String descHability) {
		this.descHability = descHability;
	}

	public Champs getChamps() {
		return champs;
	}

	public void setChamps(Champs champs) {
		this.champs = champs;
	}
	
	
	
}
