package com.mobilebuilds.controller;

import java.io.IOException;
import java.util.ArrayList;
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
	List<String> names = new ArrayList<String>();

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
	
	@RequestMapping("criarChamps")
	public ModelAndView createAllChamps() {
		ModelAndView modelAndView = new ModelAndView("adminIndex");
		
		createInDataBase();
		
		return modelAndView;
	}
	
	public void createInDataBase() {
		createNames();
		saveIntoDB();
		
	}
	
	public void saveIntoDB() {
		List<String> types = new ArrayList<String>();
		types.add("passive");
		types.add("q");
		types.add("w");
		types.add("e");
		types.add("r");
		for(String nome : names) {
			Champs champ = new Champs();
			champ.setNome(nome);
			champ.setIconChampHREF("https://raw.githubusercontent.com/gabriwl165/wild-rift-images/main/Champs_Icons/"+nome+"Square.png");
			champ.setImageChampHREF("https://raw.githubusercontent.com/gabriwl165/wild-rift-images/main/Champ/"+nome+".jpg");
			champRepository.save(champ);
			for(int i  = 0; i <=4 ;i++) {
				Habilities hability = new Habilities();
				hability.setChamps(champ);
				hability.setIconHabilityHREF("https://raw.githubusercontent.com/gabriwl165/wild-rift-images/main/Skills_Icons/"+nome+"/"+nome.toLowerCase()+"_"+i+".jpg");
				hability.setNome(types.get(i));
				habilitiesRepository.save(hability);
			}
		}
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
	
	public void createNames() {
		names.add("Ahri");
		names.add("Akali");
		names.add("Akshan");
		names.add("Alistar");
		names.add("Amumu");
		names.add("Annie");
		names.add("Ashe");
		names.add("Aurelion Sol");
		names.add("Blitzcrank");
		names.add("Braum");
		names.add("Camille");
		names.add("Corki");
		names.add("Darius");
		names.add("Diana");
		names.add("DrMundo");
		names.add("Draven");
		names.add("Evelynn");
		names.add("Ezreal");
		names.add("Fiora");
		names.add("Fizz");
		names.add("Galio");
		names.add("Garen");
		names.add("Gragas");
		names.add("Graves");
		names.add("Irelia");
		names.add("Janna");
		names.add("Jarvan IV");
		names.add("Jax");
		names.add("Jhin");
		names.add("Jinx");
		names.add("Kai'sa");
		names.add("Katarina");
		names.add("Kennen");
		names.add("Kha'zix");
		names.add("Lee Sin");
		names.add("Leona");
		names.add("Lucian");
		names.add("Lulu");
		names.add("Lux");
		names.add("Malphite");
		names.add("Master YI");
		names.add("Miss Fortune");
		names.add("Nami");
		names.add("Nasus");
		names.add("Olaf");
		names.add("Orianna");
		names.add("Pantheon");
		names.add("Rakan");
		names.add("Rammus");
		names.add("Renekton");
		names.add("Rengar");
		names.add("Riven");
		names.add("Senna");
		names.add("Seraphine");
		names.add("Shyvana");
		names.add("Singed");
		names.add("Sona");
		names.add("Soraka");
		names.add("Teemo");
		names.add("Tristana");
		names.add("Tryndamere");
		names.add("Twisted Fate");
		names.add("Varus");
		names.add("Vayne");
		names.add("Vi");
		names.add("Wukong");
		names.add("Xayah");
		names.add("Xin Zhao");
		names.add("Yasuo");
		names.add("Zed");
		names.add("Ziggs");
	}
	
	
}
