package com.akartkam.spitter.controller;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.akartkam.spitter.domain.Spitter;
import com.akartkam.spitter.exceptions.ImageUploadException;
import com.akartkam.spitter.service.SpitterService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/spitters")
// Корневой путь в URL
public class SpitterController {
	private final SpitterService spitterService;

	@Value("#{spitterProperties['spitter.webRootPath']}")
	private String webRootPath;

	@Inject
	public SpitterController(SpitterService spitterService) {
		this.spitterService = spitterService;
	}

	@RequestMapping(method = POST)
	public String addSpitterFromForm(@Valid Spitter spitter,
			BindingResult bindingResult,
			@RequestParam(value = "image", required = false)// Прием файла
			MultipartFile image) {
		if (bindingResult.hasErrors()) { // Проверка ошибок
			return "spitters/edit";
		}
		spitterService.saveSpitter(spitter); // Сохранить объект Spitter

		try {
			if (!image.isEmpty()) {
				validateImage(image); // Проверить изображение
				saveImage(spitter.getId() + ".jpg", image); // Сохранить файл
			}
		} catch (ImageUploadException e) {
			bindingResult.reject(e.getMessage());
			return "spitters/edit";
		}

		return "redirect:/spitters/" + spitter.getUsername(); // Переадресовать
	}

	private void validateImage(MultipartFile image) {
		if (!image.getContentType().equals("image/jpeg")) {
			throw new ImageUploadException("Only JPG images accepted");
		}
	}
	
	private void saveImage(String filename, MultipartFile image)
			throws ImageUploadException {
		try {
			File file = new File(webRootPath + "\\resources\\" + filename);
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		} catch (IOException e) {
			throw new ImageUploadException("Unable to save image", e);
		}
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
	
	@RequestMapping(method = GET, params = "new")
	public String createSpitterProfile(Model model) {
		Spitter spitter = new Spitter();//spitterService.getSpitter(3L);
		model.addAttribute(spitter);
		return "spitters/edit";
	}
	@RequestMapping(value="/{username}", method=GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		model.addAttribute(spitterService.getSpitter(username));
		return "spitters/view";
	}	
}