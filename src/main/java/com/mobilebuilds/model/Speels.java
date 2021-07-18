package com.mobilebuilds.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Speels {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String iconHREF;
	
	private String nome;
	
	@Column(columnDefinition="TEXT", length=2560)
	private String descSpeel;

	@ManyToOne
	public Build builds;
	
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

	public String getDesc() {
		return descSpeel;
	}

	public void setDesc(String desc) {
		this.descSpeel = desc;
	}

	public Build getBuilds() {
		return builds;
	}

	public void setBuilds(Build builds) {
		this.builds = builds;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
