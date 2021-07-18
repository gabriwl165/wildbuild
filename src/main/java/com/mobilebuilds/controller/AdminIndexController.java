package com.mobilebuilds.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mobilebuilds.model.Build;
import com.mobilebuilds.model.Champs;
import com.mobilebuilds.model.Habilities;
import com.mobilebuilds.repository.BuildRepository;
import com.mobilebuilds.repository.ChampRepository;
import com.mobilebuilds.repository.HabilitiesRepository;
import com.mobilebuilds.repository.ItensRepository;
import com.mobilebuilds.repository.SpeelRepository;

@Controller
public class AdminIndexController {
	
	@Autowired
	private ChampRepository champRepository;
	
	@Autowired
	private HabilitiesRepository habilitiesRepository;
	
	@Autowired
	private ItensRepository itensRepository;
	
	@Autowired
	private BuildRepository buildRepository;
	
	@Autowired
	private SpeelRepository speelRepository;
	
	Habilities Passivehabilities = null;
	Habilities Qhabilities = null;
	Habilities Whabilities = null;
	Habilities Ehabilities = null;
	Habilities Rhabilities = null;
	HashMap<String, Object> map = new HashMap<String, Object>();
	
	
	@RequestMapping("/connectAdmin")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("adminIndex");
		modelAndView.addObject("champs", champRepository.findAll());
		
		return modelAndView;
	}
	
	@RequestMapping("/adminLoadChamp/{idchamp}")
	public ModelAndView admin(@PathVariable ("idchamp") Long idChamp) {
		ModelAndView modelAndView = new ModelAndView("adminChampControl"); 
		createModelsToResponse(idChamp);
		modelAndView.addAllObjects(map);
		return modelAndView;
	}
	
	public void createModelsToResponse(Long idChamp){
		isAnyRepositoryNull(idChamp);
		map.put("champ", champRepository.findById(idChamp).get());
		map.put("passive", Passivehabilities);
		map.put("q", Qhabilities);
		map.put("w", Whabilities);
		map.put("e", Ehabilities);
		map.put("r", Rhabilities);
		map.put("itens", itensRepository.getAllItensWhitoutEnchment());
		map.put("build", new Build());
		map.put("builds", buildRepository.findAll());
		map.put("speels", speelRepository.findAll());
		map.put("encantamentos",  itensRepository.getEnchatments());
		
	}
	
	public void isAnyRepositoryNull(Long idChamp){
		if(habilitiesRepository.FindPassiveHabilite(idChamp) == null) {
			Passivehabilities = new Habilities();
		} else {
			setPassive(idChamp);
		}
		
		if(habilitiesRepository.FindQHability(idChamp) == null) {
			Qhabilities = new Habilities();
		} else {
			setQ(idChamp);
		}
		
		if(habilitiesRepository.FindWHability(idChamp) == null) {
			Whabilities = new Habilities();
		} else {
			setW(idChamp);
		}
		
		if(habilitiesRepository.FindEHability(idChamp) == null) {
			Ehabilities = new Habilities();
		} else {
			setE(idChamp);
		}
		
		if(habilitiesRepository.FindRHability(idChamp) == null) {
			Rhabilities = new Habilities();
		} else {
			setR(idChamp);
		}
	}
	
	public void setPassive(Long idChamp) {
		Passivehabilities = habilitiesRepository.FindPassiveHabilite(idChamp);
	}
	
	public void setQ(Long idChamp) {
		Qhabilities = habilitiesRepository.FindQHability(idChamp);
	}
	
	public void setW(Long idChamp) {
		Whabilities = habilitiesRepository.FindWHability(idChamp);
	}
	
	public void setE(Long idChamp) {
		Ehabilities = habilitiesRepository.FindEHability(idChamp);
	}
	
	public void setR(Long idChamp) {
		Rhabilities = habilitiesRepository.FindRHability(idChamp);
	}
	
}
