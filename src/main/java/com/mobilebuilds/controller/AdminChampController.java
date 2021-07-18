package com.mobilebuilds.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.ArrayUtils;

import com.mobilebuilds.model.*;
import com.mobilebuilds.repository.BuildRepository;
import com.mobilebuilds.repository.ChampRepository;
import com.mobilebuilds.repository.HabilitiesRepository;
import com.mobilebuilds.repository.ItensRepository;
import com.mobilebuilds.repository.SpeelRepository;

@Controller
public class AdminChampController {

	@Autowired
	private ChampRepository champRepository;
	
	@Autowired
	private HabilitiesRepository habilitiesRepository;
	
	@Autowired
	private BuildRepository buildRepository;
	
	@Autowired
	private ItensRepository itensRepository;
	
	@Autowired
	private SpeelRepository speelRepository;
	
	
	private Champs champ = new Champs();

	Habilities Passivehabilities = null;
	Habilities Qhabilities = null;
	Habilities Whabilities = null;
	Habilities Ehabilities = null;
	Habilities Rhabilities = null;
	HashMap<String, Object> map = new HashMap<String, Object>();

	@RequestMapping("adicionarChamp")
	public ModelAndView adicionar() {
		return new ModelAndView("addChamp");
	}
	
	@RequestMapping("salvarChamp")
	public ModelAndView salvarChamp(Champs champ) {
		ModelAndView modelAndView = new ModelAndView("adminIndex");
		champRepository.save(champ);
		modelAndView.addObject("champs", champRepository.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "**/salvarPassiva/{idchamp}", method = RequestMethod.POST)
	public ModelAndView salvarPassiva(Habilities hability,@PathVariable ("idchamp") long idChamp) {
		ModelAndView modelAndView = new ModelAndView("adminChampControl");
		
		saveHability(hability, idChamp, "passive");
		createModelsToResponse(idChamp);
		modelAndView.addAllObjects(map);

		return modelAndView;
	}
	
	@RequestMapping(value = "**/salvarQ/{idchamp}", method = RequestMethod.POST)
	public ModelAndView salvarQ(Habilities hability, @PathVariable("idchamp") Long idChamp) {
		ModelAndView modelAndView = new ModelAndView("adminChampControl");
		
		saveHability(hability, idChamp, "q");
		createModelsToResponse(idChamp);
		modelAndView.addAllObjects(map);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "**/salvarW/{idchamp}", method = RequestMethod.POST)
	public ModelAndView salvarW(Habilities hability, @PathVariable("idchamp") Long idChamp) {
		ModelAndView modelAndView = new ModelAndView("adminChampControl");
		
		saveHability(hability, idChamp, "w");
		createModelsToResponse(idChamp);
		modelAndView.addAllObjects(map);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "**/salvarE/{idchamp}", method = RequestMethod.POST)
	public ModelAndView salvarE(Habilities hability, @PathVariable("idchamp") Long idChamp) {
		ModelAndView modelAndView = new ModelAndView("adminChampControl");
		
		saveHability(hability, idChamp, "e");
		createModelsToResponse(idChamp);
		modelAndView.addAllObjects(map);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "**/salvarR/{idchamp}", method = RequestMethod.POST)
	public ModelAndView salvarR(Habilities hability, @PathVariable("idchamp") Long idChamp) {
		ModelAndView modelAndView = new ModelAndView("adminChampControl");
		
		saveHability(hability, idChamp, "r");
		createModelsToResponse(idChamp);
		modelAndView.addAllObjects(map);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "**/salvarBuild/{idchamp}", method = RequestMethod.POST)
	public ModelAndView salvarBuild(Build build ,@PathVariable("idchamp") Long idChamp) {
		ModelAndView modelAndView = new ModelAndView("adminChampControl");
		
		saveBuild(build, idChamp);
		createModelsToResponse(idChamp);
		modelAndView.addAllObjects(map);
		
		return modelAndView;
	}
	
	public void saveBuild(Build build, Long idChamp) {
		build.setChamp(champRepository.findById(idChamp).get());
		buildRepository.save(build);
	}
	
	public void saveHability(Habilities hability, Long idChamp, String type) {
		hability.setNome(type);
		hability.setChamps(champRepository.findById(idChamp).get());
		habilitiesRepository.save(hability);
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
	
	
	public ChampRepository getChampRepository() {
		return champRepository;
	}

	public void setChampRepository(ChampRepository champRepository) {
		this.champRepository = champRepository;
	}

	public Champs getChamp() {
		return champ;
	}

	public void setChamp(Champs champ) {
		this.champ = champ;
	}
	
	
	
}
