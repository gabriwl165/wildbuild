package com.mobilebuilds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mobilebuilds.model.Items;
import com.mobilebuilds.repository.ItensRepository;

@Controller
public class AdminItemController {

	@Autowired
	private ItensRepository itensRepository;
	
	@RequestMapping(value = "adicionarItem")
	public ModelAndView adicionar() {
		ModelAndView modelAndView = new ModelAndView("addItem");
		return modelAndView;
	}
	
	@RequestMapping(value = "salvarItem")
	public ModelAndView salvar(Items item) {
		ModelAndView modelAndView = new ModelAndView("adminItensControl");
		itensRepository.save(item);
		modelAndView.addObject("itens", itensRepository.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "**/itens")
	public ModelAndView itens() {
		ModelAndView modelAndView = new ModelAndView("adminItensControl");
		modelAndView.addObject("itens", itensRepository.findAll());
		return modelAndView;
	}
	
	
}
