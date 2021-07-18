package com.mobilebuilds.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Build {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Champs champ;
	
	@ManyToMany
	private List<Items> itens;
	
	@ManyToMany
	private List<Speels> speels;
	
	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Champs getChamp() {
		return champ;
	}

	public void setChamp(Champs champ) {
		this.champ = champ;
	}

	public List<Items> getItens() {
		return itens;
	}

	public void setItens(List<Items> itens) {
		this.itens = itens;
	}

	public List<Speels> getSpeels() {
		return speels;
	}

	public void setSpeels(List<Speels> speels) {
		this.speels = speels;
	}

	
	
	
}
