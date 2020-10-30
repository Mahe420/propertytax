package com.project.propertytax.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.propertytax.service.DetailsService;

@Controller
public class MainController {

	@Autowired
	DetailsService detailsService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@GetMapping(value = "/index")
	public String index() {
		LOGGER.info("Entered index page");
		return "index";
	}

	@GetMapping(value = "/report")
	public String report(ModelMap map) {
		LOGGER.info("Entered report page");
		Map<String, Map<String, Long>> mapDisplay = new HashMap();
		try {
			mapDisplay = detailsService.reportDetails();
		} catch (Exception e) {
			LOGGER.error("Exception :{}", e.getMessage());
			return "redirect:error";
		}
		map.addAttribute("map", mapDisplay);
		return "report";
	}

	@GetMapping(value = "/error")
	public String error(ModelMap map) {
		LOGGER.info("Entered error page");
		return "error";
	}

	@GetMapping(value = "/*")
	public String redirect() {
		LOGGER.info("Redirect other mapping to index");
		return "redirect:index";
	}
}
