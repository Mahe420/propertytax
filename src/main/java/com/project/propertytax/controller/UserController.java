package com.project.propertytax.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.propertytax.entity.JsonResponse;
import com.project.propertytax.entity.User;
import com.project.propertytax.exception.ContructionYearException;
import com.project.propertytax.exception.UnableRetrieveException;
import com.project.propertytax.service.DetailsService;
import com.project.propertytax.service.UserService;

@Controller
public class UserController {

	@Autowired
	DetailsService detailsService;

	@Autowired
	UserService userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@GetMapping(value = "/user")
	public String form(ModelMap model) {
		LOGGER.info("Entered user mapping to load form");
		List<String> zoneList = new ArrayList<String>();
		List<String> descriptionList = new ArrayList<String>();
		List<String> statusList = new ArrayList<String>();
		try {
			zoneList = detailsService.allZoneNames();
			descriptionList = detailsService.allDescription();
			statusList = detailsService.allStatus();
		} catch (Exception e) {
			LOGGER.error("Exception :{}", e.getMessage());
			return "redirect:error";
		}

		model.addAttribute("user", new User());
		model.addAttribute("zone", zoneList);
		model.addAttribute("description", descriptionList);
		model.addAttribute("status", statusList);
		return "user";
	}

	@PostMapping(value = "/user")
	public String insertUser(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model) {

		LOGGER.info("Entered user mapping to insert User Data");

		List<String> zoneList = new ArrayList<String>();
		List<String> descriptionList = new ArrayList<String>();
		List<String> statusList = new ArrayList<String>();
		try {
			zoneList = detailsService.allZoneNames();
			descriptionList = detailsService.allDescription();
			statusList = detailsService.allStatus();
		} catch (Exception e) {
			LOGGER.error("Exception :{}", e.getMessage());
			return "redirect:error";
		}

		if (result.hasErrors()) {
			model.addAttribute("zone", zoneList);
			model.addAttribute("description", descriptionList);
			model.addAttribute("status", statusList);
			return "user";
		} else {
			String success;
			try {
				success = userService.insertUser(user);
			} catch (UnableRetrieveException e) {
				LOGGER.error("Exception :{}", e.getMessage());
				return "redirect:error";
			}
			LOGGER.info("Message from service {}", success);
			return "redirect:index";
		}
	}

	@PostMapping(value = "/calc")
	public @ResponseBody JsonResponse calculateTax(@Valid User user, BindingResult result, ModelMap model) {

		LOGGER.info("Entered calc mapping for AJAX call to calculate Tax");
		Map<String, Object> map = new HashMap<String, Object>();
		JsonResponse json = new JsonResponse();
		float totalTax = 0;

		if (result.hasFieldErrors("yearAssessment") || result.hasFieldErrors("constructedYear")
				|| result.hasFieldErrors("area")) {
			LOGGER.info("Set FAILURE scenario");
			json.setStatus("FAIL");

			map.put("yearAssessment", result.getFieldError("yearAssessment"));
			map.put("constructedYear", result.getFieldError("constructedYear"));
			map.put("area", result.getFieldError("area"));

			json.setResult(map);
			return json;
		}
		try {
			totalTax = userService.calculateTax(user);
		} catch (UnableRetrieveException e) {
			LOGGER.error("Exception :{}", e.getMessage());

		} catch (ContructionYearException e) {
			LOGGER.error("Error in year of Construction");
			json.setStatus("FAIL");
			map.put("yearAssessment", result.getFieldError("yearAssessment"));
			map.put("constructedYear", result.getFieldError("constructedYear"));
			map.put("area", result.getFieldError("area"));
			map.put("exceed", "Error in year of Construction");
			json.setResult(map);
			return json;
		}

		LOGGER.info("Set SUCCESS scenario");
		json.setResult(totalTax);
		json.setStatus("SUCCESS");

		return json;
	}

}
