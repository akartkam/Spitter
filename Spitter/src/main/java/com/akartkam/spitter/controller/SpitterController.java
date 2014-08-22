package com.akartkam.spitter.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import com.akartkam.spitter.domain.Spitter;
import com.akartkam.spitter.service.SpitterService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/spitters")
// Корневой путь в URL
public class SpitterController {
	private final SpitterService spitterService;

	@Inject
	public SpitterController(SpitterService spitterService) {
		this.spitterService = spitterService;
	}

	// Обрабатывает GET-запросы к URL /spitter/spittles
	@RequestMapping(value = "/spittles", method = GET)
	public String listSpittlesForSpitter(
			@RequestParam("spitter") String username, Model model) {
		Spitter spitter = spitterService.getSpitter(username);
		model.addAttribute(spitter); // Заполнение модели
		model.addAttribute(spitterService.getSpittlesForSpitter(username));
		return "spittles/list";
	}
}