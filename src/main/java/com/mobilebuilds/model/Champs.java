package com.mobilebuilds.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Champs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	@OneToMany
	private List<Habilities> habilities;
	
	@OneToMany
	public List<Build> builds;
	
	private String imageChampHREF;
	
	private String iconChampHREF;
	
	private String bannerChampHREF;
	
	
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

	public List<Habilities> getHabilities() {
		return habilities;
	}

	public void setHabilities(List<Habilities> habilities) {
		this.habilities = habilities;
	}

	public String getIconChampHREF() {
		return iconChampHREF;
	}

	public void setIconChampHREF(String iconChampHREF) {
		this.iconChampHREF = iconChampHREF;
	}


	
}
