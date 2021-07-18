package com.mobilebuilds.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String iconHREF;
	
	@Column(columnDefinition="TEXT", length=2560)
	private String descItem;

	private String tipo;
	
	private String nome;
	
	@ManyToOne
	private Build builds;
	
	public Build getBuilds() {
		return builds;
	}

	public void setBuilds(Build builds) {
		this.builds = builds;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIconHREF() {
		return iconHREF;
	}

	public void setIconHREF(String iconHREF) {
		this.iconHREF = iconHREF;
	}

	public String getDescItem() {
		return descItem;
	}

	public void setDescItem(String descItem) {
		this.descItem = descItem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
