package com.mobilebuilds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mobilebuilds.model.Speels;
import com.mobilebuilds.repository.SpeelRepository;

@Controller
public class AdminSpeelController {

	@Autowired
	private SpeelRepository speelRepository;
	
	@RequestMapping("adicionarSpeel")
	public ModelAndView addSpeel() {
		ModelAndView modelAndView = new ModelAndView("addSpeel");
		modelAndView.addObject("speels", speelRepository.findAll());
		return modelAndView;
	}
	
	@RequestMapping("salvarSpeel")
	public ModelAndView salvar(Speels speel) {
		ModelAndView modelAndView = new ModelAndView("addSpeel");
		
		speelRepository.save(speel);
		modelAndView.addObject("speels", speelRepository.findAll());
		
		return modelAndView;
	}
	
	@RequestMapping("**/speels")
	public ModelAndView speel() {
		ModelAndView modelAndView = new ModelAndView("adminSpeelsControl");
		modelAndView.addObject("speels", speelRepository.findAll());
		return modelAndView;
	}
	
	
}
